package egovframework.com.ext.jstree.springiBatis.core.dao;

import egovframework.com.cmm.service.impl.EgovComiBatisAbstractDAO;
import egovframework.com.ext.jstree.springiBatis.core.vo.ComprehensiveTree;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("supportDao")
@Qualifier("supportDao")
public class SupportDaoImpl extends EgovComiBatisAbstractDAO implements SupportDao{

    @Override
    public <T extends ComprehensiveTree> void ddlExecute(T comprehensiveTree) throws Exception {
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_1", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_2", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_3", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_4", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_5", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_6", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_7", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_8", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlExecute_comment_9", comprehensiveTree);
    }

    @Override
    public <T extends ComprehensiveTree> void ddlSequenceExecute(T comprehensiveTree) throws Exception {
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlSequenceExecute", comprehensiveTree);
    }

    @Override
    public <T extends ComprehensiveTree> void dmlExecute(T comprehensiveTree) throws Exception {
        update(comprehensiveTree.getSqlMapSelector() + "." + "dmlExecute_1", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "dmlExecute_2", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "dmlExecute_3", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "dmlExecute_4", comprehensiveTree);
    }

    @Override
    public <T extends ComprehensiveTree> void ddlLogExecute(T comprehensiveTree) throws Exception {
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_1", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_2", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_3", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_4", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_5", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_6", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_7", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_8", comprehensiveTree);
        update(comprehensiveTree.getSqlMapSelector() + "." + "ddlLogExecute_comment_9", comprehensiveTree);
    }

    @Override
    public <T extends ComprehensiveTree> int isExistTable(T comprehensiveTree) throws Exception {
        return (int) getSqlMapClientTemplate().queryForObject(
                comprehensiveTree.getSqlMapSelector() + ".isExistTable", comprehensiveTree);
    }
}
