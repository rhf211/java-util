package generate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * gb_address_black_list
 * @author 
 */
@Data
public class GbAddressBlackList {
    private Integer id;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String phone;

    private String caseId;

    private Integer orderId;

    private Integer userId;

    private String email;

    private String status;

    private String reasonType;

    private String reasonCode;

    private String comment;

    private Integer recCreatorId;

    private Date recCreateTime;

    private Date recUpdateTime;

    private String whiteUserId;

    private Short isCouponAbuse;

    private Short isDeliveryForbidden;

}