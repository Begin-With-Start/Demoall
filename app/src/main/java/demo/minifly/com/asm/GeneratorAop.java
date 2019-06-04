package demo.minifly.com.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class GeneratorAop {

    public static void generator() {
//        LogUtils.showErrLog("" + MyAop.class.getName());
//        ClassReader classReader = new ClassReader(MyAop.class.getName());

        File classFile = new File("./build/intermediates/classes/debug/demo/minifly/com/asm/MyAop.class");
        InputStream is;
        try {

            is = new FileInputStream(classFile);

            ClassReader classReader = new ClassReader(is);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddSecurityClassAdapter(classWriter);

            classReader.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = classWriter.toByteArray();
            FileOutputStream fout = new FileOutputStream(classFile);
            fout.write(data);
            fout.close();

        } catch (Exception e) {

        }


//        ClassReader cr = new TraceClassReader("MyAop");
//        TraceClassWriter cw = new TraceClassWriter(cr, ClassWriter.COMPUTE_FRAMES, MobileApplication.getApplicationInstance().getClassLoader());
//        ClassAdapter classAdapter = new AddSecurityClassAdapter(cw);
//        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//        byte[] data = cw.toByteArray();
//        File file = new File("MyAop.class");
//        FileOutputStream fout = new FileOutputStream(file);
//        fout.write(data);
//        fout.close();
    }

}
