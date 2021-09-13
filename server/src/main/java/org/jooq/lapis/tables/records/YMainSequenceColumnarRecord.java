/*
 * This file is generated by jOOQ.
 */
package org.jooq.lapis.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.lapis.tables.YMainSequenceColumnar;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class YMainSequenceColumnarRecord extends UpdatableRecordImpl<YMainSequenceColumnarRecord> implements Record2<Integer, byte[]> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>y_main_sequence_columnar.position</code>.
     */
    public void setPosition(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>y_main_sequence_columnar.position</code>.
     */
    public Integer getPosition() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>y_main_sequence_columnar.data_compressed</code>.
     */
    public void setDataCompressed(byte[] value) {
        set(1, value);
    }

    /**
     * Getter for <code>y_main_sequence_columnar.data_compressed</code>.
     */
    public byte[] getDataCompressed() {
        return (byte[]) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, byte[]> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, byte[]> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return YMainSequenceColumnar.Y_MAIN_SEQUENCE_COLUMNAR.POSITION;
    }

    @Override
    public Field<byte[]> field2() {
        return YMainSequenceColumnar.Y_MAIN_SEQUENCE_COLUMNAR.DATA_COMPRESSED;
    }

    @Override
    public Integer component1() {
        return getPosition();
    }

    @Override
    public byte[] component2() {
        return getDataCompressed();
    }

    @Override
    public Integer value1() {
        return getPosition();
    }

    @Override
    public byte[] value2() {
        return getDataCompressed();
    }

    @Override
    public YMainSequenceColumnarRecord value1(Integer value) {
        setPosition(value);
        return this;
    }

    @Override
    public YMainSequenceColumnarRecord value2(byte[] value) {
        setDataCompressed(value);
        return this;
    }

    @Override
    public YMainSequenceColumnarRecord values(Integer value1, byte[] value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached YMainSequenceColumnarRecord
     */
    public YMainSequenceColumnarRecord() {
        super(YMainSequenceColumnar.Y_MAIN_SEQUENCE_COLUMNAR);
    }

    /**
     * Create a detached, initialised YMainSequenceColumnarRecord
     */
    public YMainSequenceColumnarRecord(Integer position, byte[] dataCompressed) {
        super(YMainSequenceColumnar.Y_MAIN_SEQUENCE_COLUMNAR);

        setPosition(position);
        setDataCompressed(dataCompressed);
    }
}
