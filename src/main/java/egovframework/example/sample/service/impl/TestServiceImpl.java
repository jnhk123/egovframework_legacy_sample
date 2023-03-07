package egovframework.example.sample.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.TestService;

@Service
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService {
	
	private final TestMapper testMapper;

	public TestServiceImpl(TestMapper testMapper) {
		this.testMapper = testMapper;
	}
	
	@Override
	public Map<String, Object> selectMethod() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		map.put("data", testMapper.selectTest());
		return map;
	}

}
