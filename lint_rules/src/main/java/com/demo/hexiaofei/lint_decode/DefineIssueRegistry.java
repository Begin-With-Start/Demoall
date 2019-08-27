package com.demo.hexiaofei.lint_decode;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create by minifly on 2019-08-08 17:49
 * description:
 */
public class DefineIssueRegistry extends IssueRegistry {
    static {
        System.out.println("***************************************************");
        System.out.println("**************** lint 读取配置文件 *****************");
        System.out.println("***************************************************");
//        LoadPropertiesFile.loadPropertiesFile();
    }

    @Override
    public List<Issue> getIssues() {
        System.out.println("***************************************************");
        System.out.println("**************** lint 开始静态分析代码 *****************");
        System.out.println("***************************************************");
        return Arrays.asList(
                LogRuleDetector.ISSUE
        );
    }
}
