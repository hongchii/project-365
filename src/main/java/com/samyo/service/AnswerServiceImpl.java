package com.samyo.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samyo.domain.AnswerCountVO;
import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;

import com.samyo.mapper.AnswerMapper;

@Service("answerService")

public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int insertAnswer(AnswerVO answer) throws Exception {
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.insertAnswer(answer);
		return 1;
	}


	@Override
	public List<AnswerVO> readAnswer(int question_num, int member_num) throws Exception {

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("question_num", question_num);
		map.put("member_num", member_num);
		
		List<AnswerVO> result =answerMapper.selectAnswer(map);
		return result;
	}


	@Override
	public AnswerVO updateAnswerPage(int answer_num) {

		System.out.println("수정 페이지/ service name: UpdateAnswerPage");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		
		AnswerVO result =answerMapper.updateAnswerPage(answer_num);
		return result;
	}


	@Override
	public void updateAnswer(AnswerVO answer) {
		
		System.out.println("수정 하기!/ service name: UpdateAnswer");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
	
		answerMapper.updateAnswer(answer);
		
	}


	@Override
	public int updateDelete(AnswerVO answer) {
		
		System.out.println("삭제 여부 수정 하기!/ service name: UpdateDelete");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
	
		answerMapper.updateDelete(answer);
		
		return 1;//성공
		
	}


	@Override
	public void publicAnswer(AnswerVO answer) {
		System.out.println("공개 여부 수정 하기!/ service name: publicAnswer");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
	
		answerMapper.publicAnswer(answer);
		
	}


	@Override
	public int trashPublic(AnswerVO answer) {
		System.out.println("답변 복원 하기 !/ service name: TrashPublic");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
	
		answerMapper.trashPublic(answer);
		
		return 1;//성공
		
	}


	@Override
	public List<AnswerVO> readTrash(int member_num) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		
		List<AnswerVO> result =answerMapper.readTrash(member_num);
		return result;
		
	}


	//answer_count테이블에 답변이 존재하는지 확인
	@Override
	public int count(AnswerCountVO answercount) {
		System.out.println("답변 잇는지확인 !/ service name: count");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		String result = answerMapper.count(answercount);

		if(result!=null) {
			return 0;//up또는 down으로
			
		}
		else {
			return 1;//setting으로
		}
		
	}

	//최초 답변등록시 answer_count테이블 insert
	@Override
	public void setCount(AnswerCountVO answercount) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.setCount(answercount);
		
		
	}


	//답변 횟수 업(등록)
	@Override
	public int updateCountUp(AnswerCountVO answercount) {
		System.out.println("답변count !/ service name: updateCount");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		
		int result = answerMapper.updateCountUp(answercount);
		
		if(result==1) {
			
			return 1;
		}
		else {
			return 0;
		}
		
	}

	//답변횟수 다운 (삭제)
	@Override
	public int updateCountDown(AnswerCountVO answercount) {
		System.out.println("답변count !/ service name: updateCountDown");
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.updateCountDown(answercount);
		System.out.println("answercount");
		return 1;// 성공
	}

	@Override
	public int deleteAnswer(int answer_num) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.deleteAnswer(answer_num);
		return 1;//성공
	}
}
