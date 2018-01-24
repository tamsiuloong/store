package com.yaorange.store.entity;

import java.util.List;

/**
 * ��ҳʵ��(���������ҳ��Ϣ����ʾlist����)
 * @author Administrator
 *
 */
public class Page {
	private List list;
	
	private Integer currPage;//��ǰҳ
	private Integer totalPage;//�ܹ�����ҳ
	private Integer pageSize=12;//ÿҳ��ʾ����������
	private Integer totalCount;//�ܹ�����������

	
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
	 * ��ȡ��ʼ��
	 * @return
	 */
	public Integer getBeginRow() {
		return (currPage-1)*pageSize;
	}
	
	/**
	 * ��ȡ��һҳ
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
	 * ��ȡ��һҳ
	 * @return
	 */
	public Integer getNextPage(){
		Integer result = 0;
		result = currPage+1;
		if(result>getTotalPage())
		{
			result = totalPage;
		}
		return result;
	}
	
	
	
}
