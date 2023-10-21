package com.buba.controller;

import com.buba.pojo.Book;
import com.buba.service.BookService;
import com.buba.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/list")
    public List<Book> list() throws Exception{
        return bookService.list();
    }
    @PostMapping("/list")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        bookService.addOrUpdate(book);
        return book;
    }
    @PostMapping("/delete")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }
    @GetMapping("/cate/{cid}/list")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }
    @GetMapping("/search")
    public List<Book> searchResult(@RequestParam("keywords")String keywords) {
        // 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return bookService.list();
        } else {
            return bookService.Search(keywords);
        }
    }
    @PostMapping("/covers")
    public String covers(MultipartFile file) throws Exception {
        String folder = "D:/upload/img";
        File image = new File(folder);
        File f = new File(image, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8081/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
