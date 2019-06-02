package demo.minifly.com.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class GeneratorAop {

    public static void generator() throws IOException {
        LogUtils.showErrLog("" + MyAop.class.getName());
//        ClassReader classReader = new ClassReader(MyAop.class.getName());
        TraceClassReader classReader = new TraceClassReader("MyAop" , GeneratorAop.class.getClassLoader());
        TraceClassWriter classWriter = new TraceClassWriter(classReader,ClassWriter.COMPUTE_MAXS ,GeneratorAop.class.getClassLoader() );
        ClassAdapter classAdapter = new AddSecurityClassAdapter(classWriter);

        classReader.accept(classAdapter,ClassReader.SKIP_DEBUG);
        byte [] data = classWriter.toByteArray();
        File file = new File("MyAop.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }

}
