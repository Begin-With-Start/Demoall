package demo.minifly.com.asm;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

public class TraceClassReader extends ClassReader {

    private static ClassLoader classLoader;

    public TraceClassReader(byte[] b) {
        super(b);
    }

    public TraceClassReader(byte[] b, int off, int len) {
        super(b, off, len);
    }

    public TraceClassReader(InputStream is) throws IOException {
        super(is);
    }

    public TraceClassReader(final String name , MyClassLoader classLoader) throws IOException {
        this(classLoader.getSystemResourceAsStream(name.replace('.', '/')
                + ".class"));
        this.classLoader = classLoader;
    }

    public static URL getSystemResource(String name) {
        ClassLoader system = classLoader;
        if (system == null) {
            return ClassLoader.getBootstrapResource(name);
        }
        return system.getResource(name);
    }
}

class MyClassLoader extends  ClassLoader{
    private static ClassLoader loader;

    public MyClassLoader(ClassLoader loader) {
        this.loader = loader;
    }


    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return super.getResources(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        return super.getResourceAsStream(name);
    }


    public static URL getSystemResource(String name) {
        ClassLoader system = loader;
        return system.getResource(name);
    }
}
