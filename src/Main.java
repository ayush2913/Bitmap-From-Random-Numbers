import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        int totalNumbers = 128;
        String url = "https://www.random.org/integers/?num="+Integer.toString(totalNumbers)+"&min=0&max=255&col=1&base=10&format=plain&rnd=new";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        int[] response = new int[128];
        int index = 0;
        while ((inputLine = in.readLine()) != null) {
            int input = Integer.parseInt(inputLine.trim());
            response[index] = input;
            index++;
        }
        in.close();

        //print result
        System.out.println();

        int width = 128;
        int height = 128;
        int[] data = new int[128*128*3];
        index=0;
        for(int i=0; i<height; i++){
            int point = response[i];
            for(int j=0; j<width; j++){
                data[index+j*3] = point;
                data[index+j*3+1] = point;
                data[index+j*3+2] = point;
            }
            index += height*3;
        }

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        img.getRaster().setPixels(0, 0, 128, 128, data);

        JLabel jLabel = new JLabel(new ImageIcon(img));

        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);
        JFrame r = new JFrame();
        r.add(jPanel);
        r.show();

    }
}
