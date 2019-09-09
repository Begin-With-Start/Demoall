package demo.minifly.com.designpattern.simplefactory;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({CarType.AudiType ,CarType.AwmType , CarType.JiliType})
public @interface CarType {
    int AudiType = 0 ;
    int AwmType = 1 ;
    int JiliType = 2 ;
}
