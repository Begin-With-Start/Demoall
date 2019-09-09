//package com.demo.hexiaofei.lint_decode;
//
//import android.util.Log;
//
//import com.android.tools.lint.client.api.JavaEvaluator;
//import com.android.tools.lint.detector.api.LogRuleDetector;
//import com.android.tools.lint.detector.api.Issue;
//import com.android.tools.lint.detector.api.JavaContext;
//import com.android.tools.lint.detector.api.Severity;
//import com.android.tools.lint.detector.api.SourceCodeScanner;
//import com.intellij.openapi.diagnostic.LogUtil;
//import com.intellij.psi.PsiMethod;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.jetbrains.uast.UCallExpression;
//
//import java.util.ArrayList;
//import java.util.List;

/**
 * create by minifly on 2019-08-08 16:34
 * description:
 * LogRuleDetector.JavaScanner——扫描 Java 源码抽象语法树，在25.2.0版本中该接口已被弃用，换成了 JavaPsiScanner，
 * 对语法分析由 Lombok AST API 转变 IntelliJ IDEA's "PSI" API，
 * 功能更强大而且可以扩展到 kotlin 语言上（kotlin 是由 intellij 推出的与 Java 无缝兼容的全新语言）
 * LogRuleDetector.ClassScanner——扫描 class 文件
 * LogRuleDetector.BinaryResourceScanner——扫描二进制资源文件
 * LogRuleDetector.ResourceFolderScanner——扫描资源文件
 * LogRuleDetector.XmlScanner——扫描xml文件
 * LogRuleDetector.GradleScanner——扫描gradle文件
 * LogRuleDetector.OtherFileScanner——扫描其他类型文件
 */
//public class ClassDecoder extends LogRuleDetector implements SourceCodeScanner {
//
//    public final static Issue issue = Issue.create("testLintIssueId","briefDescription" , "扩展 explanation" ,null,2, Severity.ERROR,null );
//
//    @Nullable
//    @Override
//    public List<String> getApplicableMethodNames() {
//        List<String> result = new ArrayList<String>(){};
//        result.add("findview");
//        return result;
//    }
//
//    @Override
//    public void visitMethod(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {
//        JavaEvaluator javaEvaluator = context.getEvaluator();
//        if(javaEvaluator.isMemberInClass(method,"demo.minifly.com.fuction_demo.desk.DeskActivity") && javaEvaluator.getParameterCount(method) == 0 ){
//            System.out.println("这就是我们要的目标方法了；");
//            Log.e("ClassDecoder" ,"这就是我们要的目标方法了；" );
//
//            context.report(issue, node, context.getLocation(node),
//                    "我是一个消息哦");
//
//        }
//    }
//
//
//
//}
