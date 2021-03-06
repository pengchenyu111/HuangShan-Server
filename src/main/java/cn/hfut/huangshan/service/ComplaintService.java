package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.ComplaintMapper;
import cn.hfut.huangshan.pojo.Complaint;
import cn.hfut.huangshan.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 投诉
 * @author pcy
 */
@Service
public class ComplaintService {

    @Autowired
    ComplaintMapper complaintMapper;


    /**
     * 全查询
     * @return
     */
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = complaintMapper.getAllComplaints();
        return complaints;
    }

    /**
     * 查询最近的
     * @return
     */
    public List<Complaint> getRecent() {
        List<Complaint> complaints = complaintMapper.getRecent();
        return complaints;
    }

    /**
     * 根据id查询
     * @param id 投诉id
     * @return
     */
    public Complaint getOneById(long id) {
        Complaint complaint = complaintMapper.getOneById(id);
        return complaint;
    }

    /**
     * 获取投诉分类排行榜
     * @return
     */
    public List<String> getRanks() {
        List<String> ranks =  complaintMapper.getRanks();
        return ranks;
    }


    /**
     * 根据日期查询
     * @param date
     * @return
     */
    public List<Complaint> getByDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = new Date();
        try {
            temp = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatDate = simpleDateFormat.format(temp);
        List<Complaint> complaints = complaintMapper.getByDate(formatDate);
        return complaints;
    }

    /**
     * 增加一个
     * @param complaint
     * @return
     */
    @Transactional
    public boolean addOne(Complaint complaint) {
        complaint.setComplaintTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//等价于new Date().toLocaleString()
        complaint.setIsHandle("0");
        Integer rows = complaintMapper.addOne(complaint);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOne(long id) {
        Integer rows = complaintMapper.deleteOne(id);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 全更新一个
     * @param complaint
     * @return
     */
    @Transactional
    public boolean updateOne(Complaint complaint) {
        //这里有个问题，MySQL不允许你插入或更新一个为空的时间，所以这里逼不得已再全更新时设置了时间，反正都是管理员才能更新，没啥影响
        complaint.setHandleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Integer rows = complaintMapper.updateOne(complaint);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 管理员处理一个
     * @param id
     * @param handleAdminName
     * @param handleMessage
     * @return
     */
    @Transactional
    public boolean handleOne(long id, String handleAdminName, String handleMessage) {
        String handleTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Integer rows = complaintMapper.handleOne(id,handleAdminName,handleTime,handleMessage);
        if (rows > 0){
            return true;
        }
        return false;
    }

}
