/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.renderscript.cts;

import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import com.android.cts.stub.R;

public class FdimTest extends RSBaseCompute {
    private ScriptC_fdim_f32 script_f32;
    private Allocation mIn;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        script_f32 = new ScriptC_fdim_f32(mRS);
    }

    @Override
    public void forEach(int testId, Allocation mIn, Allocation mOut) throws RSRuntimeException {
        switch (testId) {
        case TEST_F32:
            script_f32.forEach_fdim_f32_1(mIn, mOut);
            break;
        case TEST_F32_2:
            script_f32.forEach_fdim_f32_2(mIn, mOut);
            break;
        case TEST_F32_3:
            script_f32.forEach_fdim_f32_3(mIn, mOut);
            break;
        case TEST_F32_4:
            script_f32.forEach_fdim_f32_4(mIn, mOut);
            break;
        }
    }

    @Override
    protected float[] getRefArray(float[] in, int input_size, int stride, int skip) {
        float[] ref = new float[input_size * stride];
        for (int i = 0; i < input_size; i++) {
            for (int j = 0; j < stride - skip; j++) {
                int idx = i * stride * 2 + j;
                ref[i * (stride - skip) + j] = (float)(Math.max(0.0, (double)in[idx] - (double)in[idx+stride]));
            }
        }
        return ref;
    }

    @Override
    protected Allocation setInAlloc(Element e) {
        return mIn;
    }

    @Override
    protected float[] makeInArray(int size) {
        return new float[size*2];
    }

    public void testfdimF32() {
        ScriptField_fdim_f32_input floatArray = new ScriptField_fdim_f32_input(mRS, INPUTSIZE);
        mIn = floatArray.getAllocation();
        doF32(0x12678, 0);
    }

    public void testfdimF32_2() {
        ScriptField_fdim_f32_2_input floatArray = new ScriptField_fdim_f32_2_input(mRS, INPUTSIZE);
        mIn = floatArray.getAllocation();
        doF32_2(0x1af45, 0);
    }

    public void testfdimF32_3() {
        ScriptField_fdim_f32_3_input floatArray = new ScriptField_fdim_f32_3_input(mRS, INPUTSIZE);
        mIn = floatArray.getAllocation();
        doF32_3(0x1cd345, 0);
    }

    public void testfdimF32_4() {
        ScriptField_fdim_f32_4_input floatArray = new ScriptField_fdim_f32_4_input(mRS, INPUTSIZE);
        mIn = floatArray.getAllocation();
        doF32_4(0x1ca45, 0);
    }
}
