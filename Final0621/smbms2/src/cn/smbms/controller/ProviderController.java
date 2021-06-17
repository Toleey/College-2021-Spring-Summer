package cn.smbms.controller;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Pager;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/provider")
public class ProviderController {

    @Resource(name = "providerService")
    private ProviderService providerService;
    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }

    @RequestMapping("/provider.do")
    public String providerList(
            @RequestParam(value = "queryProCode",required = false) String proCode,
            @RequestParam(value = "queryProName",required = false) String proName,
            @RequestParam(value = "pageIndex",required = false) String currentPageNum,
            Model model
    ){
        model.addAttribute("queryProCode",proCode);
        model.addAttribute("queryProName",proName);
        //当前页
        int currentpage = 1;
        if (currentPageNum!=null && !"".equals(currentPageNum)){
            currentpage = Integer.parseInt(currentPageNum);
        }
        model.addAttribute("pageCount",currentpage);

        //总条数
        int rowCount = providerService.findProviderCountByProCodeAndProName(proCode,proName);
        model.addAttribute("totalCount",rowCount);

        //每页显示条数
        int rowPerPage = 5;

        //总页数
        Pager pager = new Pager(rowCount,rowPerPage,currentpage);
        int pageCount = pager.getPageCount();

        model.addAttribute("totalPageCount",pageCount);
        model.addAttribute("queryProName",proName);
        model.addAttribute("currentPageNo",currentpage);
        //获得分页记录
        int fromLineNum=(currentpage-1)*rowPerPage;

        List<Provider> providerList = providerService.findProviderListByProCodeAndProName(proCode,proName,fromLineNum,rowPerPage);
        model.addAttribute("providerList",providerList);

        return "providerlist";
    }

    //进入新增供应商的界面
    @RequestMapping(value = "/provideradd.html",method = RequestMethod.GET)
    public String addProvider(@ModelAttribute("provider") Provider provider){
        return "provideradd";
    }


    //添加供应商的操作
    @RequestMapping(value = "/addProviderSave.do",method = RequestMethod.POST)
    public String addProviderSave(Provider provider,
                                  @RequestParam(value = "attachs",required = false) MultipartFile[]multipartFile
            , HttpSession session, HttpServletRequest request){

        MultipartFile companyLicPic = multipartFile[0];
        MultipartFile orgCodePic = multipartFile[1];

        String companyLicPicPath = "";
        String orgCodePicPath = "";

        if (companyLicPic.isEmpty()){
            request.setAttribute("uploadFileError","企业营业执照未上传");
            return "provideradd";
        }else if (orgCodePic.isEmpty()){
            request.setAttribute("uploadOcError","组织机构代码证未上传");
            return "provideradd";
        }else {
            String uploadPath = request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfile");

            String oldCompanyLicPicName = companyLicPic.getOriginalFilename();
            String oldCompanyLicPicNameSuffix = FilenameUtils.getExtension(oldCompanyLicPicName);
            String oldOrgCodePicName = orgCodePic.getOriginalFilename();
            String oldOrgCodePicNameSuffix = FilenameUtils.getExtension(oldOrgCodePicName);

            //companyLicPic
            if (oldCompanyLicPicNameSuffix.equals("jpg") || oldCompanyLicPicNameSuffix.equals("jpeg") //后缀判断
                    || oldCompanyLicPicNameSuffix.equals("png") || oldCompanyLicPicNameSuffix.equals("pneg")) {
                long size = companyLicPic.getSize() / 1024; //kb
                if (size > 0 && size < 500) { //大小判断
                    String newCompanyLicPicFileName =   //设置新文件名
                            System.currentTimeMillis()+ RandomUtils.nextInt(10000)+"_companyLicPic."+oldCompanyLicPicNameSuffix;
                    companyLicPicPath+=uploadPath+File.separator+newCompanyLicPicFileName;//拼接新文件路径及名称
                    File newCompanyLicPicFile = new File(companyLicPicPath);//对目标路径建立映射
                    File file = new File(uploadPath);//如果没有目录就新建
                    if(!file.exists()){file.mkdir();}
                    try {
                        companyLicPic.transferTo(newCompanyLicPicFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    provider.setCompanyLicPicPath(companyLicPicPath);
                }else{ //文件大小不符合
                    request.setAttribute("uploadFileError","文件大小不符合");
                    return "provideradd";
                }
            }else{ //后缀不符合 跳过
                request.setAttribute("uploadFileError","文件后缀不符合");
                return "provideradd";
            }

            //orgCodePic

            if (oldOrgCodePicNameSuffix.equals("jpg") || oldOrgCodePicNameSuffix.equals("jpeg") //后缀判断
                    || oldOrgCodePicNameSuffix.equals("png") || oldOrgCodePicNameSuffix.equals("pneg")) {
                long size = orgCodePic.getSize() / 1024; //kb
                if (size > 0 && size < 500) { //大小判断
                    String newOrgCodePicFileName =   //设置新文件名
                            System.currentTimeMillis()+ RandomUtils.nextInt(10000)+"_orgCodePic."+oldOrgCodePicNameSuffix;
                    orgCodePicPath+=uploadPath+File.separator+newOrgCodePicFileName;//拼接新文件路径及名称
                    File newOrgCodePicFile = new File(orgCodePicPath);//对目标路径建立映射
                    File file = new File(uploadPath);//如果没有目录就新建
                    if(!file.exists()){file.mkdir();}
                    try {
                        orgCodePic.transferTo(newOrgCodePicFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    provider.setOrgCodePicPath(orgCodePicPath);
                }else{ //文件大小不符合
                    request.setAttribute("uploadOcError","文件大小不符合");
                    return "provideradd";
                }
            }else{ //后缀不符合 跳过
                request.setAttribute("uploadOcError","文件后缀不符合");
                return "provideradd";
            }

        }


        User loginUser = (User) session.getAttribute("userSession");
        provider.setCreatedBy(loginUser.getId());
        provider.setCreationDate(new Timestamp(new Date().getTime()));
        providerService.addProvider(provider);
        return "redirect:/sys/provider/provider.do";
    }

    //进入修改供应商页面
    @RequestMapping(value = "/modifyProvider.html",method = RequestMethod.GET)
    public String modifyProvider(
            @ModelAttribute("provider") Provider provider, @RequestParam("proid") String proid, Model model
    ){
        Integer id = null;
        if (proid!=null){
            id = Integer.parseInt(proid);
        }
        Provider provider2 = providerService.findProviderById(id);
        model.addAttribute("provider",provider2);
        return "providermodify";
    }

    //提交供应商修改信息
    @RequestMapping(value = "/modifyProviderSave.do",method = RequestMethod.POST)
    public String modifyProviderSave(@Valid Provider provider, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()) {
            return "/sys/provider/providermodify";
        }
        User loginUser = (User) session.getAttribute("userSession");
        provider.setModifyBy(loginUser.getId());
        provider.setModifyDate(new Timestamp(new Date().getTime()));
        System.out.println(provider);
        boolean boo = providerService.modifyProvider(provider);
        if (!boo){
            return "providermodify";
        }
        return "redirect:/sys/provider/provider.do";
    }

    //查看供应商信息
    @RequestMapping(value = "/viewProvider.do",method = RequestMethod.GET)
    public String viewProvider(@RequestParam("proid") Integer proid,Model model){
        Provider provider = providerService.findViewProviderById(proid);
        model.addAttribute("provider",provider);
        return "providerview";
    }

    //删除供应商信息
    @RequestMapping(value = "/deleteProvider.do",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteProvider(@RequestParam("proid") Integer proid){
        boolean result = providerService.deleteProviderById(proid);
        String data = null;
        if (result){
            data = "true";
        }else {
            data = "false";
        }
        String dataStr = JSON.toJSONString(data);
        return dataStr;
    }

    //ajax查看供应商
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public Object viewAjaxUser(@RequestParam("id") String id){
        Provider provider = new Provider();
        if (id == null || "".equals(id)){
            return JSON.toJSONString("nodata");
        }else {
            try {
                Integer proId = Integer.parseInt(id);
                provider = providerService.findViewProviderById(proId);
            }catch (Exception e){ //数据用可能传过来不是数字
                e.printStackTrace();
                return JSON.toJSONString("failed");
            }

        }
        return JSON.toJSONString(provider);
    }

}
