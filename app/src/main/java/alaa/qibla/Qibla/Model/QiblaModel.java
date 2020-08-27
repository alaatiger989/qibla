package alaa.qibla.Qibla.Model;

public class QiblaModel {
    public float bearTo;
    public float head;
    public float direction;

    public QiblaModel(float bearTo, float head , float direction) {
        this.bearTo = bearTo;
        this.head = head;
        this.direction = direction;
    }

    public float getDirection() {
        return direction;
    }

    public float getBearTo() {
        return bearTo;
    }

    public float getHead() {
        return head;
    }
}
