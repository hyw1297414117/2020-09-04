package com.project.app.imperial.vo;

import com.project.app.imperial.domain.ImpMainnoTaskno;
import com.project.app.imperial.domain.ImpTaskBasic;

public class ImpTaskBasicVo {
    private ImpTaskBasic impTaskBasic;
    private ImpMainnoTaskno impMainnoTaskno;

    public ImpTaskBasic getImpTaskBasic() {
        return impTaskBasic;
    }

    public void setImpTaskBasic(ImpTaskBasic impTaskBasic) {
        this.impTaskBasic = impTaskBasic;
    }

    public ImpMainnoTaskno getImpMainnoTaskno() {
        return impMainnoTaskno;
    }

    public void setImpMainnoTaskno(ImpMainnoTaskno impMainnoTaskno) {
        this.impMainnoTaskno = impMainnoTaskno;
    }
}
