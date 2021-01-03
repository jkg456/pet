package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.rain.bean.*;
import com.rain.util.DBUtil;
/**
 * 关于宠物连接数据库的所有操作的类
 */
public class PetDao {

	/**
	 * 添宠物信息，传入所有的信息
	 * @param card
	 * @param name
	 * @param type
	 * @param autho
	 * @param press
	 * @param num
	 */
	public void addPet(String card, String name, String type, String autho, String press, int num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert  into pet(card,name,type,autho,press,num) values(?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, card);
			stm.setString(2, name);
			stm.setString(3, type);
			stm.setString(4, autho);
			stm.setString(5, press);
			stm.setInt(6, num);
			rs = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有的宠物信息，返回的是ArrayList数组形式
	 * @return
	 */
	public ArrayList<PetBean> get_ListInfo(){
		ArrayList<PetBean> tag_Array = new ArrayList<PetBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from pet";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				PetBean tag = new PetBean();
				tag.setBid(rs.getInt("bid"));
				tag.setName(rs.getString("name"));
				tag.setCard(rs.getString("card"));
				tag.setType(rs.getString("type"));
				tag.setAutho(rs.getString("autho"));
				tag.setPress(rs.getString("press"));
				tag.setNum(rs.getInt("num"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * 获取寄养记录的全部信息，传入的条件有status，aid，表示搜索正在寄养的，或者已经领宠的信息，aid代表当前登录用户
	 * @param status
	 * @return
	 */
	public ArrayList<HistoryBean> get_HistoryListInfo(int status,String aid){
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from history where aid='"+aid+"' and status='"+status+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				HistoryBean tag = new HistoryBean();
				tag.setHid(rs.getInt("hid"));
				tag.setAid(rs.getInt("aid"));
				tag.setBid(rs.getInt("bid"));
				tag.setBookname(rs.getString("bookname"));
				tag.setCard(rs.getString("card"));
				tag.setAdminname(rs.getString("adminname"));
				tag.setUsername(rs.getString("username"));
				tag.setBegintime(rs.getString("begintime"));
				tag.setEndtime(rs.getString("endtime"));
				tag.setStatus(rs.getInt("status"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * 获取寄养记录的全部信息，传入的条件有status，表示搜索正在寄养的，或者已经领宠的信息
	 * @param status
	 * @return
	 */
	public ArrayList<HistoryBean> get_HistoryListInfo2(int status){
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from history where status='"+status+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				HistoryBean tag = new HistoryBean();
				tag.setHid(rs.getInt("hid"));
				tag.setAid(rs.getInt("aid"));
				tag.setBid(rs.getInt("bid"));
				tag.setBookname(rs.getString("bookname"));
				tag.setCard(rs.getString("card"));
				tag.setAdminname(rs.getString("adminname"));
				tag.setUsername(rs.getString("username"));
				tag.setBegintime(rs.getString("begintime"));
				tag.setEndtime(rs.getString("endtime"));
				tag.setStatus(rs.getInt("status"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * 获取单个宠物的信息，根据传入的bid来查找，返回一个BookBean数据类型
	 * @param bid
	 * @return
	 */
	public PetBean get_PetInfo(int bid){
		PetBean tag = new PetBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from pet where bid='"+bid+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				tag.setBid(rs.getInt("bid"));
				tag.setName(rs.getString("name"));
				tag.setCard(rs.getString("card"));
				tag.setType(rs.getString("type"));
				tag.setAutho(rs.getString("autho"));
				tag.setPress(rs.getString("press"));
				tag.setNum(rs.getInt("num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag;
	}
	/**
	 * 修改宠物的信息，bid作为条件，
	 */
	public void updatePet(int bid, String card, String name, String type, String autho, String press, int num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update pet set name=?,card=?,type=?,autho=?,press=?,num=? where bid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, card);
			stm.setString(3, type);
			stm.setString(4, autho);
			stm.setString(5, press);
			stm.setInt(6, num);
			stm.setInt(7, bid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除宠物信息，根据传入的bid作为条件
	 * @param bid
	 */
	public void deletePet(int bid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from pet where bid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, bid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(uid);

	}
	/**
	 * 用户查找宠物，根据输入的名称，使用like进行模糊查询，然后返回一个ArrayList数组类型
	 * @param name
	 * @return
	 */
	public ArrayList<PetBean> getLikeList(String name) {
		// TODO Auto-generated method stub
		ArrayList<PetBean> tag_Array = new ArrayList<PetBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from pet where name like '%"+name+"%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				PetBean tag = new PetBean();
				tag.setBid(rs.getInt("bid"));
				tag.setName(rs.getString("name"));
				tag.setCard(rs.getString("card"));
				tag.setType(rs.getString("type"));
				tag.setAutho(rs.getString("autho"));
				tag.setPress(rs.getString("press"));
				tag.setNum(rs.getInt("num"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * 宠物寄养函数，根据传入bid宠物id，adminbean当前登录用户的信息，在寄养记录数据表中新插入一条记录
	 * @param bid
	 * @param adminbean
	 */
	public void borrowPet(int bid, AdminBean adminbean) {
		// TODO Auto-generated method stub
		PetBean petbean = new PetBean();
		petbean = this.get_PetInfo(bid);
		//生成日期的功能
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		//生成寄养开始日期
		String begintime = ""+year+"-"+month+"-"+day;
		month = month + 1;
		//生成截止领宠日期
		String endtime = ""+year+"-"+month+"-"+day;
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert  into history(aid,bid,card,bookname,adminname,username,begintime,endtime,status) values(?,?,?,?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, adminbean.getAid());
			stm.setInt(2, petbean.getBid());
			stm.setString(3, petbean.getCard());
			stm.setString(4, petbean.getName());
			stm.setString(5, adminbean.getUsername());
			stm.setString(6, adminbean.getName());
			stm.setString(7, begintime);
			stm.setString(8, endtime);
			stm.setInt(9, 1);
			rs = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 领宠功能的函数，根据传入的hid寄养记录id，讲status字段的值改为0，并将领宠日期改变为当前日期
	 * @param hid
	 */
	public void borrowPet2(int hid) {
		// TODO Auto-generated method stub
		//生成日期
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		//生成还书日期
		String endtime = ""+year+"-"+month+"-"+day;
		Connection conn = DBUtil.getConnectDb();
		String sql = "update history set endtime=?,status=? where hid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, endtime);
			stm.setInt(2, 0);
			stm.setInt(3, hid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
