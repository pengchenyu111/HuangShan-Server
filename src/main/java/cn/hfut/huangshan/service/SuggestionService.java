package cn.hfut.huangshan.service;


import cn.hfut.huangshan.mapper.SuggestionMapper;
import cn.hfut.huangshan.pojo.Suggestion;
import cn.hfut.huangshan.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuggestionService {

    @Autowired
    SuggestionMapper suggestionMapper;


    /**
     * 全查询
     * @return
     */
    public List<Suggestion> getAll() {
        List<Suggestion> suggestions = suggestionMapper.getAll();
        return suggestions;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Suggestion getById(long id) {
        Suggestion suggestion = suggestionMapper.getById(id);
        return  suggestion;
    }

    /**
     * 增加一个
     * @param suggestion
     * @return
     */
    @Transactional
    public boolean addOne(Suggestion suggestion) {
        String formatDateTime = DateFormatUtil.toDateTime(suggestion.getFeedbackTime());
        suggestion.setFeedbackTime(formatDateTime);
        Integer rows = suggestionMapper.addOne(suggestion);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 全更新一个
     * @param suggestion
     * @return
     */
    @Transactional
    public boolean updateOne(Suggestion suggestion) {
        String formatDateTime = DateFormatUtil.toDateTime(suggestion.getFeedbackTime());
        suggestion.setFeedbackTime(formatDateTime);
        Integer rows = suggestionMapper.updateOne(suggestion);
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
        Integer rows = suggestionMapper.deleteOne(id);
        if (rows > 0){
            return true;
        }
        return false;
    }
}
