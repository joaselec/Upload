package com.my.rest.model;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 

 
@Repository
public class BoardDAOImpl implements BoardDAO {
    @Inject
    SqlSession SqlSession;

    @Override
    public void create(BoardVO vo) throws Exception {
        SqlSession.insert("board.insert", vo);
    }

    @Override
    public BoardVO read(int bno) throws Exception {
        return SqlSession.selectOne("board.view", bno);
    }

    @Override
    public void update(BoardVO vo) throws Exception {
        SqlSession.update("board.updateArticle", vo);
 
    }

    @Override
    public void delete(int bno) throws Exception {
        SqlSession.delete("board.deleteArticle",bno);
 
    }

    @Override
    public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchOption", searchOption);
        map.put("keyword", keyword);
        map.put("start", start);
        map.put("end", end);
        return SqlSession.selectList("board.listAll", map);
    }



    @Override
    public void increaseViewcnt(int bno) throws Exception {
        SqlSession.update("board.increaseViewcnt", bno);
    }
	

    @Override
    public int countArticle(String searchOption, String keyword) throws Exception {

        Map<String, String> map = new HashMap<String, String>();
        map.put("searchOption", searchOption);
        map.put("keyword", keyword);
        return SqlSession.selectOne("board.countArticle", map);
    }

 
}
