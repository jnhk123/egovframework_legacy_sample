package egovframework.example.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

@Repository
public class ChartDataRepository {

	private List<ChartData> datas;
	
	public String getAllDatas() {
		return new Gson().toJson(datas);
	}
	
	public ChartDataRepository() {
		this.datas = new ArrayList<ChartData>();
	}
	
	public synchronized boolean add(ChartData cd) {
		return datas.add(cd);
	}

	// TODO 삭제 및 수정 등등 데이터는 구현해봐도 좋을 듯.
}
