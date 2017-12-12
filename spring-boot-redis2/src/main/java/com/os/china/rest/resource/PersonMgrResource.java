package com.os.china.rest.resource;
/*
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.os.china.domain.entity.Person;
import com.os.china.dto.JsonResp;
import com.os.china.rest.service.PersonService;

/**
 * @author <a href="zhangpengfei@wxchina.com">ZhangPengFei</a>
 * @Discription 用户信息管理
 * @Data 2017-3-24
 * @Version 1.0.0
 */
//@Path("personMgr")
@Controller
public class PersonMgrResource {

    @Autowired
    private PersonService personService;
    /**
     * http://localhost:9100/rest/getPersonByName/feinik2
     * feinik1,2,3依次请求一下，只有第一次走了dao查询，后面的都是直接从缓存查询出来的
     * @param username
     * @return
     */
    @RequestMapping("/getPersonByName/{username}")
    @ResponseBody
    public Object getPersonByName(@PathVariable("username") String username) {
    	System.out.println("-----------------------2135432452345---------------------------------");
        Person person = personService.getPersonByName(username);
        return person;
    }

//    @POST
//    @Path("removePersonByName")
//    @Produces(MediaType.APPLICATION_JSON)
    public JsonResp removePersonByName(String username) {
        if (personService.removePersonByName(username)) {
            return JsonResp.success();
        }
        return JsonResp.fail("系统错误！");
    }

//    @POST
//    @Path("savePerson")
//    @Produces(MediaType.APPLICATION_JSON)
    public JsonResp savePerson(Person person) {
        if (personService.isExistPersonName(person)) {
            return JsonResp.fail("用户名已存在！");
        }
        if (personService.savePerson(person).getId() > 0) {
            return JsonResp.success();
        }
        return JsonResp.fail("系统错误！");
    }
}
