//package com.ljm.cloud.consumer.ribbon;
//
//import com.netflix.client.config.DefaultClientConfigImpl;
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.*;
//import org.springframework.cloud.netflix.ribbon.RibbonClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 配置全局Ribbon
// * @author lijunming
// * @date 2018/9/19 下午11:13
// */
//@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
//public class RibbonClientDefaultConfiguration {
//    public static class BazServiceList extends ConfigurationBasedServerList {
//        public BazServiceList(IClientConfig config) {
//            super.initWithNiwsConfig(config);
//        }
//    }
//}
//    @Configuration
//    class  DefaultRibbonConfig{
//        @Bean
//        public IRule ribbonRule(){
//            return new BestAvailableRule();
//        }
//
//        @Bean
//        public IPing  ribbonPing(){
//            return new PingUrl();
//        }
//
//        @Bean
//        public ServerList<Server> ribbonServerList(IClientConfig con){
//            return  new RibbonClientDefaultConfiguration.BazServiceList(con);
//        }
//
//        @Bean
//        public ServerListSubsetFilter serverListSubsetFilter(){
//            ServerListSubsetFilter filter=new ServerListSubsetFilter();
//            return filter;
//        }
//
//        @Bean
//        public IClientConfig  iClientConfig(){
//            return new DefaultClientConfigImpl();
//        }
//}
