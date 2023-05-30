package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;

/* loaded from: classes.dex */
public class ResolutionAnchor extends ResolutionNode {
    public static final int BARRIER_CONNECTION = 5;
    public static final int CENTER_CONNECTION = 2;
    public static final int CHAIN_CONNECTION = 4;
    public static final int DIRECT_CONNECTION = 1;
    public static final int MATCH_CONNECTION = 3;
    public static final int UNCONNECTED = 0;
    float computedValue;
    ConstraintAnchor myAnchor;
    float offset;
    private ResolutionAnchor opposite;
    private float oppositeOffset;
    float resolvedOffset;
    ResolutionAnchor resolvedTarget;
    ResolutionAnchor target;
    int type = 0;
    private ResolutionDimension dimension = null;
    private int dimensionMultiplier = 1;
    private ResolutionDimension oppositeDimension = null;
    private int oppositeDimensionMultiplier = 1;

    public ResolutionAnchor(ConstraintAnchor constraintAnchor) {
        this.myAnchor = constraintAnchor;
    }

    public void addResolvedValue(LinearSystem linearSystem) {
        SolverVariable solverVariable = this.myAnchor.getSolverVariable();
        ResolutionAnchor resolutionAnchor = this.resolvedTarget;
        if (resolutionAnchor == null) {
            linearSystem.addEquality(solverVariable, (int) (this.resolvedOffset + 0.5f));
        } else {
            linearSystem.addEquality(solverVariable, linearSystem.createObjectVariable(resolutionAnchor.myAnchor), (int) (this.resolvedOffset + 0.5f), 6);
        }
    }

    public void dependsOn(int i2, ResolutionAnchor resolutionAnchor, int i3) {
        this.type = i2;
        this.target = resolutionAnchor;
        this.offset = i3;
        resolutionAnchor.addDependent(this);
    }

    public float getResolvedValue() {
        return this.resolvedOffset;
    }

    @Override // androidx.constraintlayout.solver.widgets.ResolutionNode
    public void remove(ResolutionDimension resolutionDimension) {
        ResolutionDimension resolutionDimension2 = this.dimension;
        if (resolutionDimension2 == resolutionDimension) {
            this.dimension = null;
            this.offset = this.dimensionMultiplier;
        } else if (resolutionDimension2 == this.oppositeDimension) {
            this.oppositeDimension = null;
            this.oppositeOffset = this.oppositeDimensionMultiplier;
        }
        resolve();
    }

    @Override // androidx.constraintlayout.solver.widgets.ResolutionNode
    public void reset() {
        super.reset();
        this.target = null;
        this.offset = 0.0f;
        this.dimension = null;
        this.dimensionMultiplier = 1;
        this.oppositeDimension = null;
        this.oppositeDimensionMultiplier = 1;
        this.resolvedTarget = null;
        this.resolvedOffset = 0.0f;
        this.computedValue = 0.0f;
        this.opposite = null;
        this.oppositeOffset = 0.0f;
        this.type = 0;
    }

    public void resolve(ResolutionAnchor resolutionAnchor, float f2) {
        int i2 = this.state;
        if (i2 == 0 || !(this.resolvedTarget == resolutionAnchor || this.resolvedOffset == f2)) {
            this.resolvedTarget = resolutionAnchor;
            this.resolvedOffset = f2;
            if (i2 == 1) {
                invalidate();
            }
            didResolve();
        }
    }

    String sType(int i2) {
        return i2 == 1 ? "DIRECT" : i2 == 2 ? "CENTER" : i2 == 3 ? "MATCH" : i2 == 4 ? "CHAIN" : i2 == 5 ? "BARRIER" : "UNCONNECTED";
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, float f2) {
        this.opposite = resolutionAnchor;
        this.oppositeOffset = f2;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public String toString() {
        if (this.state == 1) {
            if (this.resolvedTarget == this) {
                return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedOffset + "]  type: " + sType(this.type);
            }
            return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedTarget + ":" + this.resolvedOffset + "] type: " + sType(this.type);
        }
        return "{ " + this.myAnchor + " UNRESOLVED} type: " + sType(this.type);
    }

    public void update() {
        ConstraintAnchor target = this.myAnchor.getTarget();
        if (target == null) {
            return;
        }
        if (target.getTarget() == this.myAnchor) {
            this.type = 4;
            target.getResolutionNode().type = 4;
        }
        int margin = this.myAnchor.getMargin();
        ConstraintAnchor.Type type = this.myAnchor.mType;
        if (type == ConstraintAnchor.Type.RIGHT || type == ConstraintAnchor.Type.BOTTOM) {
            margin = -margin;
        }
        dependsOn(target.getResolutionNode(), margin);
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, int i2, ResolutionDimension resolutionDimension) {
        this.opposite = resolutionAnchor;
        this.oppositeDimension = resolutionDimension;
        this.oppositeDimensionMultiplier = i2;
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i2) {
        this.target = resolutionAnchor;
        this.offset = i2;
        resolutionAnchor.addDependent(this);
    }

    @Override // androidx.constraintlayout.solver.widgets.ResolutionNode
    public void resolve() {
        int i2;
        ResolutionAnchor resolutionAnchor;
        ResolutionAnchor resolutionAnchor2;
        ResolutionAnchor resolutionAnchor3;
        ResolutionAnchor resolutionAnchor4;
        ResolutionAnchor resolutionAnchor5;
        ResolutionAnchor resolutionAnchor6;
        float f2;
        float width;
        float f3;
        ResolutionAnchor resolutionAnchor7;
        boolean z = true;
        if (this.state == 1 || (i2 = this.type) == 4) {
            return;
        }
        ResolutionDimension resolutionDimension = this.dimension;
        if (resolutionDimension != null) {
            if (resolutionDimension.state != 1) {
                return;
            }
            this.offset = this.dimensionMultiplier * resolutionDimension.value;
        }
        ResolutionDimension resolutionDimension2 = this.oppositeDimension;
        if (resolutionDimension2 != null) {
            if (resolutionDimension2.state != 1) {
                return;
            }
            this.oppositeOffset = this.oppositeDimensionMultiplier * resolutionDimension2.value;
        }
        if (i2 == 1 && ((resolutionAnchor7 = this.target) == null || resolutionAnchor7.state == 1)) {
            if (resolutionAnchor7 == null) {
                this.resolvedTarget = this;
                this.resolvedOffset = this.offset;
            } else {
                this.resolvedTarget = resolutionAnchor7.resolvedTarget;
                this.resolvedOffset = resolutionAnchor7.resolvedOffset + this.offset;
            }
            didResolve();
        } else if (i2 != 2 || (resolutionAnchor4 = this.target) == null || resolutionAnchor4.state != 1 || (resolutionAnchor5 = this.opposite) == null || (resolutionAnchor6 = resolutionAnchor5.target) == null || resolutionAnchor6.state != 1) {
            if (i2 != 3 || (resolutionAnchor = this.target) == null || resolutionAnchor.state != 1 || (resolutionAnchor2 = this.opposite) == null || (resolutionAnchor3 = resolutionAnchor2.target) == null || resolutionAnchor3.state != 1) {
                if (i2 == 5) {
                    this.myAnchor.mOwner.resolve();
                    return;
                }
                return;
            }
            if (LinearSystem.getMetrics() != null) {
                LinearSystem.getMetrics().matchConnectionResolved++;
            }
            ResolutionAnchor resolutionAnchor8 = this.target;
            this.resolvedTarget = resolutionAnchor8.resolvedTarget;
            ResolutionAnchor resolutionAnchor9 = this.opposite;
            ResolutionAnchor resolutionAnchor10 = resolutionAnchor9.target;
            resolutionAnchor9.resolvedTarget = resolutionAnchor10.resolvedTarget;
            this.resolvedOffset = resolutionAnchor8.resolvedOffset + this.offset;
            resolutionAnchor9.resolvedOffset = resolutionAnchor10.resolvedOffset + resolutionAnchor9.offset;
            didResolve();
            this.opposite.didResolve();
        } else {
            if (LinearSystem.getMetrics() != null) {
                LinearSystem.getMetrics().centerConnectionResolved++;
            }
            ResolutionAnchor resolutionAnchor11 = this.target;
            this.resolvedTarget = resolutionAnchor11.resolvedTarget;
            ResolutionAnchor resolutionAnchor12 = this.opposite;
            ResolutionAnchor resolutionAnchor13 = resolutionAnchor12.target;
            resolutionAnchor12.resolvedTarget = resolutionAnchor13.resolvedTarget;
            ConstraintAnchor.Type type = this.myAnchor.mType;
            ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
            int i3 = 0;
            if (type != type2 && type != ConstraintAnchor.Type.BOTTOM) {
                z = false;
            }
            if (z) {
                f2 = resolutionAnchor11.resolvedOffset - resolutionAnchor13.resolvedOffset;
            } else {
                f2 = resolutionAnchor13.resolvedOffset - resolutionAnchor11.resolvedOffset;
            }
            if (type != ConstraintAnchor.Type.LEFT && type != type2) {
                width = f2 - r2.mOwner.getHeight();
                f3 = this.myAnchor.mOwner.mVerticalBiasPercent;
            } else {
                width = f2 - r2.mOwner.getWidth();
                f3 = this.myAnchor.mOwner.mHorizontalBiasPercent;
            }
            int margin = this.myAnchor.getMargin();
            int margin2 = this.opposite.myAnchor.getMargin();
            if (this.myAnchor.getTarget() == this.opposite.myAnchor.getTarget()) {
                f3 = 0.5f;
                margin2 = 0;
            } else {
                i3 = margin;
            }
            float f4 = i3;
            float f5 = margin2;
            float f6 = (width - f4) - f5;
            if (z) {
                ResolutionAnchor resolutionAnchor14 = this.opposite;
                resolutionAnchor14.resolvedOffset = resolutionAnchor14.target.resolvedOffset + f5 + (f6 * f3);
                this.resolvedOffset = (this.target.resolvedOffset - f4) - (f6 * (1.0f - f3));
            } else {
                this.resolvedOffset = this.target.resolvedOffset + f4 + (f6 * f3);
                ResolutionAnchor resolutionAnchor15 = this.opposite;
                resolutionAnchor15.resolvedOffset = (resolutionAnchor15.target.resolvedOffset - f5) - (f6 * (1.0f - f3));
            }
            didResolve();
            this.opposite.didResolve();
        }
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i2, ResolutionDimension resolutionDimension) {
        this.target = resolutionAnchor;
        resolutionAnchor.addDependent(this);
        this.dimension = resolutionDimension;
        this.dimensionMultiplier = i2;
        resolutionDimension.addDependent(this);
    }
}
