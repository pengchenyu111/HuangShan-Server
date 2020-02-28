package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.ComplaintMapper;
import cn.hfut.huangshan.pojo.Complaint;
import cn.hfut.huangshan.utils.IdWorker;
import com.sun.rowset.internal.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 增加一个
     * @param complaint
     * @return
     */
    @Transactional
    public Complaint addOne(Complaint complaint) {
        //设置id
        IdWorker idWorker = new IdWorker(0,0);
        long id = idWorker.nextId();
        complaint.setId(id);
        complaint.setComplaintTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//等价于new Date().toLocaleString()
        Integer rows = complaintMapper.addOne(complaint);
        if (rows > 0){
            return complaintMapper.getOneById(id);
        }
        return new Complaint();
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
    public Complaint updateOne(Complaint complaint) {
        //这里有个问题，MySQL不允许你插入或更新一个为空的时间，所以这里逼不得已再全更新时设置了时间，反正都是管理员才能更新，没啥影响
        complaint.setHandleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Integer rows = complaintMapper.updateOne(complaint);
        if (rows > 0){
            return complaintMapper.getOneById(complaint.getId());
        }
        return new Complaint();
    }

    /**
     * 管理员处理宇哥
     * @param id
     * @param handleAdminName
     * @param handleMessage
     * @return
     */
    @Transactional
    public Complaint handleOne(long id, String handleAdminName, String handleMessage) {
        String handleTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Integer rows = complaintMapper.handleOne(id,handleAdminName,handleTime,handleMessage);
        if (rows > 0){
            return complaintMapper.getOneById(id);
        }
        return new Complaint();
    }
}
