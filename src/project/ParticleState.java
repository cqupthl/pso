package project;

/**
 * @ClassName ParticleState
 * @Description: TODO
 * @Author hl
 * @Date 2019/12/7
 * @Version V1.0
 **/
class ParticleState{
    public double x;
    public double y;
//   public double z;

    public double f;//适应度，即求解函数值

    //ParticleState(double x, double y,double z) {
    ParticleState(double x, double y) {
        this.x = x;
        this.y = y;
      //  this.z=z;
    }
}
