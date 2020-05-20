package com.bfxy.rabbit.test;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.mercurius.task.annotation.ElasticJobConfig;
import org.springframework.stereotype.Component;

@Component
@ElasticJobConfig(
		name = "com.bfxy.esjob.task.test.Test2Job",
		cron = "0/10 * * * * ?",
		description = "样例定时任务",
		overwrite = true,
		shardingTotalCount = 2,
		eventTraceRdbDataSource = "datasource"
)
public class Test2Job implements SimpleJob {

	@Override
	public void execute(ShardingContext shardingContext) {
		System.err.println("执行Demo job.");
	}

}
