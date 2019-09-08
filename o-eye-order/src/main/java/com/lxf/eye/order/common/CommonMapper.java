package com.lxf.eye.order.common;

import tk.mybatis.mapper.common.*;

public interface CommonMapper<T> extends
        BaseMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        Marker {
}
