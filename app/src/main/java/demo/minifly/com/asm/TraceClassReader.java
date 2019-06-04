package demo.minifly.com.asm;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import demo.minifly.com.fuction_demo.url_config.MobileApplication;

public class TraceClassReader extends ClassReader {


    public TraceClassReader(byte[] b) {
        super(b);
    }

    public TraceClassReader(byte[] b, int off, int len) {
        super(b, off, len);
    }

    public TraceClassReader(InputStream is) throws IOException {
        super(is);
    }

    public TraceClassReader(final String name) throws IOException {
        this(MyClassLoader.getSystemResourceAsStream(name.replace('.', '/')
                + ".class"));
    }

    public static ClassLoader getClassLoader() {
        return MobileApplication.getApplicationInstance().getClassLoader();
    }
}

class MyClassLoader extends  ClassLoader{
    private static ClassLoader loader = MobileApplication.getApplicationInstance().getClassLoader();

    public MyClassLoader(ClassLoader loader) {
        this.loader = loader;
    }


    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return super.getResources(name);
    }

    public static InputStream getSystemResourceAsStream(String name) {
        URL url = getSystemResource(name);
        try {
            return url != null ? url.openStream() : null;
        } catch (IOException e) {
            return null;
        }
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
