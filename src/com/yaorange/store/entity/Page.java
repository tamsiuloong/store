package com.yaorange.store.entity;

import java.util.List;

/**
 * 分页实体(里面包含分页信息，显示list数据)
 * @author Administrator
 *
 */
public class Page {
	private List list;
	
	private Integer currPage;//当前页
	private Integer totalPage;//总共多少页
	private Integer pageSize=12;//每页显示多少条数据
	private Integer totalCount;//总共多少条数据

	
	public Page() {
		super();
	}
	public Page(String currPage, Integer totalCount) {
		if(currPage==null)
		{
			this.currPage = 1;
		}
		else
		{
			this.currPage = Integer.valueOf(currPage);
		}
		
		this.totalCount = totalCount;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	

	public Integer getTotalPage() {
		Double d = (double)totalCount/(double)pageSize;
		totalPage=(int) Math.ceil(d);
		
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 获取起始数
	 * @return
	 */
	public Integer getBeginRow() {
		return (currPage-1)*pageSize;
	}
	
	/**
	 * 获取上一页
	 * @return
	 */
	public Integer getPrePage(){
		Integer result = 0;
		result = currPage-1;
		if(result<1)
		{
			result = 1;
		}
		return result;
	}
	/**
	 * 获取下一页
	 * @return
	 */
	public Integer getNextPage(){
		Integer result = 0;
		result = currPage+1;
		if(result>totalPage)
		{
			result = totalPage;
		}
		return result;
	}
	
	
	
}
