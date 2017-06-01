package com.jyx.validation;

import javax.validation.GroupSequence;

/**
 * jsr 303 数据校验Groups优先级
 * AddValider>UpdateValider>DeleteValider
 */
@GroupSequence({AddValider.class, UpdateValider.class, DeleteValider.class, QueryValider.class})
public interface ValiderBaseLevel {
}
