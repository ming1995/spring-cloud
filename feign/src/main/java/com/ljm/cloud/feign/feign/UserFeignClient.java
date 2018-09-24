package com.ljm.cloud.feign.feign;

import com.ljm.cloud.feign.config.FeignConfiguration;
import com.ljm.cloud.feign.pojo.User;
import feign.Param;
import feign.RequestLine;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author lijunming
 * @date 2018/9/22 下午12:08
 */
@FeignClient(name="user",configuration = FeignConfiguration.class,fallbackFactory =FeignClientFallbackFactory.class)
//@FeignClient(name="user",url="http://localhost:8000"),可以指定url
public interface UserFeignClient {
//使用configuration默认配置之前的写法
//    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
//    public User findById(@PathVariable("id")Integer id);

    @RequestLine("GET /user/{id}")
    public User findById(@Param("id") Integer id);

    //使用FeignConfiguration上传文件
    //@RequestMapping(value = "upload",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    //@ResponseBody
    //String handleFileUpload(@RequestPart(value = "file")MultipartFile file);
    //get多条件请求
    //@RequestMapping(value = "/get",method = RequestMethod.GET)
    //public User findById(@RequestParam Map<String,Object> map);

    //post多条件请求
    //@RequestMapping(value = "/post",method = RequestMethod.POST)
    //public User findById(@RequestBody User user);


}
/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法
 * The fallback factory must produce instances of fallback classes that
 * implement the interface annotated by {@link FeignClient}.
 * @author 周立
 */
@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public User findById(Integer id) {
                // 日志最好放在各个fallback方法中，而不要直接放在create方法中。
                // 否则在引用启动时，就会打印该日志。
                // 详见https://github.com/spring-cloud/spring-cloud-netflix/issues/1471
                FeignClientFallbackFactory.LOGGER.info("fallback; reason was:", cause);
                User user = new User();
                user.setId(-1);
                user.setUsername("默认用户");
                return user;
            }
        };
    }
}

