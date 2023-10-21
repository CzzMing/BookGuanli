package com.buba.controller;

import com.buba.pojo.JotterArticle;
import com.buba.result.Result;
import com.buba.result.ResultFactory;
import com.buba.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class JotterController {
    @Autowired
    private JotterArticleService jotterArticleService;

    @PostMapping("/admin/content/article")
    public Result saveArticle(@RequestBody @Valid JotterArticle article){
        jotterArticleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @GetMapping("/article/{size}/{page}")
    public Result listArticles(@PathVariable("size") int size, @PathVariable("page") int page){
        return ResultFactory.buildSuccessResult(jotterArticleService.list(page-1,size));
    }

    @GetMapping("/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id){
        return ResultFactory.buildSuccessResult(jotterArticleService.findById(id));
    }

    @DeleteMapping("/admin/content/article/{id}")
    public Result delArticle(@PathVariable("id") int id){
        jotterArticleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
