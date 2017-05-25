package com.jyx.validation;

import javax.validation.GroupSequence;

/**
 * jsr 303 数据校验Groups优先级
 * ValiderLevel1>ValiderLevel2>ValiderLevel3
 */
@GroupSequence({ValiderLevel1.class, ValiderLevel2.class, ValiderLevel3.class})
public interface ValiderBaseLevel {

}
