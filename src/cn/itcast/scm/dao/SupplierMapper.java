package cn.itcast.scm.dao;

import cn.itcast.scm.entity.Supplier;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
   public int insertSupplier(Supplier supplier);
}