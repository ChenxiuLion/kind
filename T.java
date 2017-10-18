import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class T {

	public static String path = "E:\\02 文档\\尹明\\家长端切图\\家长端切图\\家长端切图\\忘记密码-重置密码\\安卓";
	
	public static void main(String[] args) {
		String[] oldName = {"icon-返回","icon-验证码"};
		String[] newName = {"icon_back","icon_code"};
		
		for(int i = 0 ; i < oldName.length; i++){
			rename(oldName[i], newName[i]);
		}
		
		System.out.println("执行完成");
	}
	
	public static void rename(String oldName,String newName){
		//目录
        File dataDir = new File(path);
        //存放目录及其子目录下的所有文件对象
        List<File> myfile = new ArrayList<File>();
        //开始遍历
        listDirectory(dataDir, myfile);
        
        for(File file : myfile){
            if(file.getName().equals(oldName + ".png")){
            	file.renameTo(new File(file.getParent() + "/" + newName + ".png"));
            }
        }
	}
	
	/**
     * 遍历目录及其子目录下的所有文件并保存
     * @param path 目录全路径
     * @param myfile 列表：保存文件对象
     */
    public static void listDirectory(File path, List<File> myfile){
        if (!path.exists()){
            System.out.println("文件名称不存在!");
        }
        else
        {
            if (path.isFile()){
                myfile.add(path);
            } else{
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++  ){
                    listDirectory(files[i], myfile);
                }
            }
        }
    }
	
}
