package com.lxf.eye.show.common;

import tk.mybatis.mapper.common.*;

public interface CommonMapper<T> extends
        BaseMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        Marker {
}
