/*
* Copyright 2014 Mingyuan Xia (http://mxia.me) and contributors
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* Contributors:
*   Mingyuan Xia
*/

package patdroid.core;

import com.google.common.collect.ImmutableList;
import patdroid.util.Log;

import java.util.HashMap;

/**
 * The base ClassDetail loader. Itself does nothing but throwing an exception.
 * Any loader extending it should do some work.
 */
public class ClassDetailLoader {
    private static final ClassNotFoundException x_x =
            new ClassNotFoundException("the bare ClassDetail loader does not load anything");
    public void load(ClassInfo ci) throws ClassNotFoundException,
            ExceptionInInitializerError, NoClassDefFoundError
    { throw x_x; }

    /**
     * Set the details of the class, usually used only by class loader
     * <p>
     * <b>Note:</b> this might start class loading if the class is not loaded yet
     * @param type the owner type
     * @param details the detailed info about the class
     */
    protected static void setDetails(ClassInfo type, ClassDetail details) {
        Log.warnwarn(type.details == null, "class is already loaded" + type);
        type.details = details;
        details.updateDerivedClasses(type);
    }
}
