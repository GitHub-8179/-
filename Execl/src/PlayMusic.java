
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
 
public class PlayMusic {
    
	public static void main(String[] args) {
		Play();
	}
//    程序退出时执行的代码
    public void doShutDownWork() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    //Toolkit.getDefaultToolkit().beep();
                    Play();
                    Play();
                } catch (Exception ex) {
                }
            }
        });
    }
    
    //播放音频文件
    public static void Play(){
//        String fileurl = "C:\\Users\\songjinchen\\Desktop\\2.wav";
        String fileurl = "\2.wav";
        try{
        	// 获取音频输入流
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));
            AudioFormat aif = ais.getFormat();  // 获取音频编码对象
            SourceDataLine sdl = null;
         // 设置数据输入
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,aif);
            sdl = (SourceDataLine)AudioSystem.getLine(info);
            sdl.open(aif);
            sdl.start();
            
            //play
            int nByte = 0;
            byte[] buffer = new byte[128];
            while(nByte != -1){
                nByte = ais.read(buffer,0,128);
                if(nByte >= 0){
                    int oByte = sdl.write(buffer, 0, nByte);
                    //System.out.println(oByte);
                }
            }
            System.out.println("ffffffff");
            sdl.stop();
        }catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动产生 catch 区块
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            // TODO 自动产生 catch 区块
            e.printStackTrace();
        }
    }
}