package project;

import java.util.Random;

/**
 * @ClassName RanDom
 * @Description: 生成随机数
 * @Author hl
 * @Date 2019/12/16
 * @Version V1.0
 **/
public class RanDom {
    Random random=new Random();
    public double rand(){
        double a=random.nextInt(3) + 1;
        if(a==3.0){
            a=a-0.1;
        }
        if(a==1.0){
            a=a+0.1;
        }
        return a;

    }


}
