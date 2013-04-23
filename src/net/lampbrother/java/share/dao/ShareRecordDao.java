package net.lampbrother.java.share.dao;

import java.util.List;

import net.lampbrother.java.share.domain.ShareRecord;

public class ShareRecordDao extends BaseDao<ShareRecord> {

	/**
	 * 得到ShareRecord
	 * 
	 * @param name
	 * @return
	 */
	public String getRecord(String name ) {
		String sql = "SELECT prename FROM share_record WHERE NAME = ? ";
		return getSingleValue(sql, name);
	}
	
	/**
	 * 更具nextname查询name
	 * 
	 * @param name
	 * @return
	 */
	public String getNextName(String nextname ) {
		String sql = "SELECT name FROM share_record WHERE nextName = ? ";
		return getSingleValue(sql, nextname);
	}

	/**
	 * 更新ShareRecotd
	 * 插入下一个人请求
	 * @param name
	 * @param preName
	 * @return
	 */
	public void updatarecotdAjax(String name, String preName,String remember) {
		String sql = "UPDATE share_record SET nextName = ?,remember=?  WHERE NAME=? ";
		update(sql, preName, remember ,name);
	}
	/**
	 * 更新ShareRecotd
	 * 更新上一个人
	 * @param name
	 * @param preName
	 * @return
	 */
	public void updatarecotdPreAjax(String name, String preName, String time,String remember) {
		String sql = "UPDATE share_record SET preName = ?,shareTime = ?,remember=?  WHERE NAME=? ";
		update(sql, preName, time, remember ,name);
	}
	/**
	 * 得到全部信息
	 * @return
	 */
	public List<ShareRecord> getAllShareRecorder() {
		String sql = "SELECT * FROM share_record ";
		return queryForList(sql);
	}
	
	/**
	 * 插入一条信息
	 * @param name
	 * @param preName
	 * @param time
	 */
	public void insertrecotdAjax(String name, String time, String remember) {
		String sql = "INSERT INTO share_record(NAME,preName, nextName,shareTime,remember) VALUES (?, null, NULL, ?,?);";
		update(sql, name ,time, remember);
	}
}
