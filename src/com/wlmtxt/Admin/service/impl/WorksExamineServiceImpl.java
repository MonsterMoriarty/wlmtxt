package com.wlmtxt.Admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wlmtxt.Admin.dao.WorksExamineDao;
import com.wlmtxt.Admin.service.WorksExamineService;
import com.wlmtxt.Works.service.WorksService;
import com.wlmtxt.domain.DO.wlmtxt_recommend;
import com.wlmtxt.domain.DO.wlmtxt_works;
import com.wlmtxt.domain.DTO.RecommendDTO;
import com.wlmtxt.domain.VO.WorksVO;

import util.TeamUtil;

public class WorksExamineServiceImpl implements WorksExamineService {
	private WorksExamineDao worksExamineDao;
	private WorksService worksService;

	public WorksExamineDao getWorksExamineDao() {
		return worksExamineDao;
	}

	public void setWorksExamineDao(WorksExamineDao worksExamineDao) {
		this.worksExamineDao = worksExamineDao;
	}

	public WorksService getWorksService() {
		return worksService;
	}

	public void setWorksService(WorksService worksService) {
		this.worksService = worksService;
	}

	@Override
	public void getworksListThreeByPage(WorksVO worksVO) {

		// int count = worksExamineDao.getCountworksListThree(worksVO);
		// worksVO.setTotalCount(count);
		// worksVO.setPageSize(10);
		// worksVO.setTotalPage((int) Math.ceil((double) count /
		// worksVO.getPageSize()));
		worksExamineDao.getworksListThreeByPage(worksVO);
	}

	@Override
	public boolean delete(String worksIdAll) {
		boolean flag = false;
		String[] arr = worksIdAll.split(",");
		for (String works_id : arr) {
			wlmtxt_works wlmtxt_work = worksExamineDao.getWorksById(works_id);
			wlmtxt_work.setWorks_deleted("1");
			String time = TeamUtil.getStringSecond();
			wlmtxt_work.setWorks_gmt_modified(time);
			if (worksExamineDao.delete(wlmtxt_work)) {
				flag = true;
				return flag;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean passed(String works_id, String passed) {
		boolean flag = false;
		;
		wlmtxt_works wlmtxt_work = worksExamineDao.getWorksById(works_id);
		String time = TeamUtil.getStringSecond();
		wlmtxt_work.setWorks_gmt_modified(time);
		if (passed.equals("1")) {
			wlmtxt_work.setWorks_passed("1");
			flag = worksExamineDao.passed(wlmtxt_work);
			worksService.addNotification(wlmtxt_work.getWorks_user_id(), "1", "审核通过作品" + wlmtxt_work.getWorks_title(),
					wlmtxt_work.getWorks_id());
			return flag;
		} else {
			wlmtxt_work.setWorks_passed("2");
			flag = worksExamineDao.passed(wlmtxt_work);
			worksService.addNotification(wlmtxt_work.getWorks_user_id(), "1", "审核不通过作品" + wlmtxt_work.getWorks_title(),
					wlmtxt_work.getWorks_id());
			return flag;
		}

	}

	@Override
	public List<RecommendDTO> listrecommend() {
		List<RecommendDTO> recommendDTOList = new ArrayList<RecommendDTO>();

		List<wlmtxt_recommend> wlmtxt_recommendList = worksExamineDao.listrecommend();
		System.out.println("aaa" + wlmtxt_recommendList);
		for (wlmtxt_recommend recommend : wlmtxt_recommendList) {
			wlmtxt_works works = worksExamineDao.getWorksById(recommend.getRecommend_works_id());
			RecommendDTO recommendDTO = new RecommendDTO();
			recommendDTO.setWorks(works);
			recommendDTO.setRecommend(recommend);
			recommendDTO.setWorksDTO(worksService.getWorksDTOByID(works.getWorks_id()));
			recommendDTOList.add(recommendDTO);
		}
		return recommendDTOList;
	}

	@Override
	public boolean addRecommend(wlmtxt_recommend recommend) {
		int maxSort = worksExamineDao.getMaxSort() + 1;
		if (maxSort <= 3) {
			recommend.setRecommend_id(TeamUtil.getUuid());
			String str = "" + maxSort;
			recommend.setRecommend_sort(str);
			String time = TeamUtil.getStringSecond();
			recommend.setRecommend_gmt_create(time);
			recommend.setRecommend_gmt_modified(time);
			if (worksExamineDao.addRecommend(recommend)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRecommend(String recommendIdAll) {
		String[] arr = recommendIdAll.split(",");
		for (String recommend_id : arr) {
			worksExamineDao.deleteAdmin(recommend_id);
			List<wlmtxt_recommend> recommendList = worksExamineDao.listrecommend();
			if (recommendList != null) {
				if (recommendList.size() == 1) {
					System.out.println("aaaaa+级");
					for (wlmtxt_recommend recommend : recommendList) {
						if (recommend.getRecommend_sort().equals("2")) {
							System.out.println("bb22");
							int sort = Integer.parseInt(recommend.getRecommend_sort()) - 1;
							String str = "" + sort;
							recommend.setRecommend_sort(str);
							String time = TeamUtil.getStringSecond();
							recommend.setRecommend_gmt_modified(time);
							worksExamineDao.updateRecommend(recommend);
						} else if (recommend.getRecommend_sort().equals("1")) {
							System.out.println("cc+11");
							int sort = Integer.parseInt(recommend.getRecommend_sort());
							String str = "" + sort;
							recommend.setRecommend_sort(str);
							String time = TeamUtil.getStringSecond();
							recommend.setRecommend_gmt_modified(time);
							worksExamineDao.updateRecommend(recommend);
						}
					}
				} else if (recommendList.size() == 2) {
					int count = 0;
					for (wlmtxt_recommend recommend : recommendList) {
						if (recommend.getRecommend_sort().equals("1")) {
							count = 1;
						}
						if (count == 1) {
							if (recommend.getRecommend_sort().equals("3")) {
								int sort = Integer.parseInt(recommend.getRecommend_sort()) - 1;
								String str = "" + sort;
								recommend.setRecommend_sort(str);
								String time = TeamUtil.getStringSecond();
								recommend.setRecommend_gmt_modified(time);
								worksExamineDao.updateRecommend(recommend);
							}
						} else if (count == 0) {
							int sort = Integer.parseInt(recommend.getRecommend_sort()) - 1;
							String str = "" + sort;
							recommend.setRecommend_sort(str);
							String time = TeamUtil.getStringSecond();
							recommend.setRecommend_gmt_modified(time);
							worksExamineDao.updateRecommend(recommend);
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean moveRecommend(String recommend_id, int moveAction) {
		wlmtxt_recommend recommend = worksExamineDao.get_Recommend_byID(recommend_id);
		List<wlmtxt_recommend> wlmtxt_recommendList = new ArrayList<wlmtxt_recommend>();
		if (moveAction == 2) {
			wlmtxt_recommendList = worksExamineDao.get_RecommendChangeValue(recommend.getRecommend_sort());
			System.out.println("下移");
			for (wlmtxt_recommend recommend2 : wlmtxt_recommendList) {
				String a, b, temp;
				a = recommend2.getRecommend_sort();
				b = recommend.getRecommend_sort();
				temp = a;
				a = b;
				b = temp;
				recommend2.setRecommend_sort(a);
				worksExamineDao.updateRecommend(recommend2);
				recommend.setRecommend_sort(b);
				worksExamineDao.updateRecommend(recommend);

			}
		} else if (moveAction == 1) {
			wlmtxt_recommendList = worksExamineDao.get_RecommendChangeValueSmall(recommend.getRecommend_sort());
			for (wlmtxt_recommend recommend1 : wlmtxt_recommendList) {

				String a, b, temp;
				a = recommend1.getRecommend_sort();
				b = recommend.getRecommend_sort();
				temp = a;
				a = b;
				b = temp;
				recommend1.setRecommend_sort(a);
				worksExamineDao.updateRecommend(recommend1);
				recommend.setRecommend_sort(b);
				worksExamineDao.updateRecommend(recommend);

			}

		}
		return true;
	}

	@Override
	public boolean getworksById(String works_id) {
		return worksExamineDao.getWorksOneById(works_id);
	}

}
