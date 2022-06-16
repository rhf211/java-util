package generate;

import generate.GbAddressBlackList;

public interface GbAddressBlackListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GbAddressBlackList record);

    int insertSelective(GbAddressBlackList record);

    GbAddressBlackList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GbAddressBlackList record);

    int updateByPrimaryKey(GbAddressBlackList record);
}