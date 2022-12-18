package com.ding.store.vo;

import lombok.Data;

import java.io.Serializable;

/** 购物车数据的Value Object类 */
@Data
public class CartVO implements Serializable {
    private static final long serialVersionUID = -820807953470097119L;
    private Integer cid;
    private Integer uid;
    private Integer gid;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartVO)) return false;

        CartVO cartVO = (CartVO) o;

        if (getCid() != null ? !getCid().equals(cartVO.getCid()) : cartVO.getCid() != null) return false;
        if (getUid() != null ? !getUid().equals(cartVO.getUid()) : cartVO.getUid() != null) return false;
        if (getGid() != null ? !getGid().equals(cartVO.getGid()) : cartVO.getGid() != null) return false;
        if (getNum() != null ? !getNum().equals(cartVO.getNum()) : cartVO.getNum() != null) return false;
        if (getTitle() != null ? !getTitle().equals(cartVO.getTitle()) : cartVO.getTitle() != null) return false;
        if (getRealPrice() != null ? !getRealPrice().equals(cartVO.getRealPrice()) : cartVO.getRealPrice() != null)
            return false;
        return getImage() != null ? getImage().equals(cartVO.getImage()) : cartVO.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = getCid() != null ? getCid().hashCode() : 0;
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + (getGid() != null ? getGid().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getRealPrice() != null ? getRealPrice().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }
}
