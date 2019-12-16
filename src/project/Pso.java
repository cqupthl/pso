package project;

import java.util.Random;

/**
 * @ClassName pso
 * @Description: TODO
 * @Author hl
 * @Date 2019/12/7
 * @Version V1.0
 **/
public class Pso {

        int n;						//粒子数量
        ParticleState[] p;			//粒子数组
        ParticleState[] v;			//速度数组
        ParticleState[] pbest;		//粒子最优解
        ParticleState gbest;		//全局最优解
        double vmax;				//最大速度
        int c1,c2,c3;					//学习参数

        public static void main(String[] args) {
            Pso e = new Pso();
            e.init();
            e.PSO(300);
        }

        //适应函数
        public void fitnessFunction( double a,double b) {
          //  double ranomDistance=0.1;
            for(int i = 0; i<n; i++) {
                p[i].f =Math.abs((p[i].x-a)*(p[i].x-a)+(p[i].y-b)*(p[i].y-b)-0.1);
           //     p[i].f =Math.abs((p[i].x-a)*(p[i].x-a)+(p[i].y-b)*(p[i].y-b)+p[i].z);
            }
        }

        //初始化
        public void init() {
            n = 1000;
            p = new ParticleState[n];
            v = new ParticleState[n];
            pbest = new ParticleState[n];
          //  gbest = new ParticleState(1.0, 3.0,-0.1);
            gbest = new ParticleState(1.0, 3.0);
            c1=c2=c3=3;
            vmax = 0.1;
         RanDom ranDom=new RanDom();

            for(int i=0;i<n;i++) {
               /* p[i]=new ParticleState(Math.random(),Math.random(),Math.random());
                v[i]=new ParticleState(Math.random()*vmax,Math.random()*vmax,Math.random()*vmax);*/

                p[i]=new ParticleState(ranDom.rand(),ranDom.rand());

                v[i]=new ParticleState(Math.random()*vmax,Math.random()*vmax);
            }

            fitnessFunction(1,3);

            //初始化粒子与集群的最优值
            gbest.f = Integer.MAX_VALUE;
            for(int i=0;i<n;i++) {
                pbest[i]=p[i];
                if(p[i].f < gbest.f) {
                    gbest = p[i];
                }
            }
          //  System.out.println("初始最优值：" +gbest.x+" "+gbest.y+" "+gbest.z+" "+gbest.f);
            System.out.println("初始最优值：" +gbest.x+" "+gbest.y+" "+gbest.f);
        }





        //粒子群算法	max-迭代次数
        public void PSO(int max) {
            for(int i=0;i<max;i++) {
                double w = 0.3;//惯性权重
                for(int j=0;j<n;j++) {
                    //更新粒子速度
                    double vx=w*v[j].x+c1*Math.random()*(pbest[j].x-p[j].x)+c2*Math.random()*(gbest.x-p[j].x);
                    double vy=w*v[j].y+c1*Math.random()*(pbest[j].y-p[j].y)+c2*Math.random()*(gbest.y-p[j].y);
                  //  double vz=w*v[j].z+c1*Math.random()*(pbest[j].z-p[j].z)+c2*Math.random()*(gbest.z-p[j].z);
                    if (vx>vmax){
                        vx=vmax;
                    }else{
                        if(vx<-vmax){
                            vx=-vmax;
                        }
                    }
                    if (vy>vmax){
                        vy=vmax;
                    }else{
                        if(vy<-vmax){
                            vy=-vmax;
                        }
                    }
                /*    if (vz>vmax){
                        vz=vmax;
                    }else{
                        if(vz<-vmax){
                            vz=-vmax;
                        }
                    }*/

                  //  v[j] = new ParticleState(vx,vy,vz);
                    v[j] = new ParticleState(vx,vy);


                    //更新粒子的位置

                        p[j].x += v[j].x;
                        p[j].y += v[j].y;

                  /*  p[j].x += v[j].x;
                    p[j].y += v[j].y;*/
                  //  p[j].z += v[j].z;
                }

                fitnessFunction(1,3);

                //更新个体极值和群体极值
                for(int j=0;j<n;j++) {
                    if(p[i].f < pbest[i].f) {
                        pbest[i] = p[i];
                    }
                    if(p[i].f < gbest.f) {
                        gbest = p[i];
                    }
                }
               // System.out.println("==="+i+"==="+gbest.x+" "+gbest.y+" "+gbest.z+" "+gbest.f);

                    System.out.println(i+"    "+gbest.x+" "+gbest.y+" "+gbest.f);


            }
        }
    }

