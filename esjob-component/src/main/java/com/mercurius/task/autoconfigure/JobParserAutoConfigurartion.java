package com.mercurius.task.autoconfigure;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.mercurius.task.parser.ElasticJobConfParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "elastic.job.zk", name = {"namespace", "serverLists"})
@EnableConfigurationProperties(JobZookeeperProperties.class)
public class JobParserAutoConfigurartion {
	@Bean(initMethod = "init")
	public ZookeeperRegistryCenter zookeeperRegistryCenter(JobZookeeperProperties jobZookeeperProperties) {
		ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(jobZookeeperProperties.getServerLists(), jobZookeeperProperties.getNamespace());
		zookeeperConfiguration.setConnectionTimeoutMilliseconds(jobZookeeperProperties.getConnectionTimeoutMilliseconds());
		zookeeperConfiguration.setSessionTimeoutMilliseconds(jobZookeeperProperties.getSessionTimeoutMilliseconds());
		zookeeperConfiguration.setMaxRetries(jobZookeeperProperties.getMaxRetries());
		zookeeperConfiguration.setMaxSleepTimeMilliseconds(jobZookeeperProperties.getMaxSleepTimeMilliseconds());
		zookeeperConfiguration.setBaseSleepTimeMilliseconds(jobZookeeperProperties.getBaseSleepTimeMilliseconds());
		log.info("初始化job注册中心配置成功,zkaddress: {},namespace: {}", jobZookeeperProperties.getServerLists(), jobZookeeperProperties.getNamespace());
		return new ZookeeperRegistryCenter(zookeeperConfiguration);
	}

	@Bean
	public ElasticJobConfParser elasticJobConfParser(JobZookeeperProperties jobZookeeperProperties, ZookeeperRegistryCenter zookeeperRegistryCenter) {
		return new ElasticJobConfParser(jobZookeeperProperties, zookeeperRegistryCenter);

	}

	/**
	 * 任务执行事件数据源
	 *
	 * @return
	 */
	@Bean("datasource")
	@ConfigurationProperties("spring.datasource.druid.log")
	public DataSource dataSourceTwo() {
		return DruidDataSourceBuilder.create().build();
	}
}
