package demo.minifly.com.fuction_demo.project_all_demo.photo_select_upload.media;

/**
 * Created by Jackie on 2017/6/20.
 */

public class UploadType {
    private String path;        //路径
    private int type;           //类型
    private int progress;       //进度
    private boolean success;    //结果，上传成功或者失败
    private boolean finish;     //上传完成

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
