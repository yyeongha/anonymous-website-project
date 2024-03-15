package com.example.askproject.Model.DAO.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.PageDAO;
import com.example.askproject.Model.Entity.PageEntity;
import com.example.askproject.Model.Repository.PageRepository;

@Service
public class PageDAOImpl implements PageDAO{
    @Autowired
    private PageRepository pageRepository;

    @Override
    public List<PageEntity> findAllPage() {
        // TODO Auto-generated method stub
        return pageRepository.findAllPage();
    }

    @Override
    public void deletePage(String pageId) {
        // TODO Auto-generated method stub
        pageRepository.deleteById(pageId);
    }

    @Override
    public void insertPage(PageEntity pageEntity) {
        // TODO Auto-generated method stub
        pageRepository.save(pageEntity);
    }

    @Override
    public void updatePage(PageEntity pageEntity) {
        // TODO Auto-generated method stub
        pageRepository.save(pageEntity);
        
    }

    @Override
    public PageEntity findByPageId(String pageId) {
        // TODO Auto-generated method stub
        return pageRepository.findByPageId(pageId);
    }

    @Override
    public List<PageEntity> findByPageIdContaining(String keyword) {
        // TODO Auto-generated method stub
        return pageRepository.findByPageIdContaining(keyword);
    }

    @Override
    public void resetPageTodayCount() {
        // TODO Auto-generated method stub
        pageRepository.resetPageTodayCount();
    }

}
