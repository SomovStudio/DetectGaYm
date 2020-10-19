using System;
using System.Collections.Generic;
using System.Text;

namespace TestsEditor
{
    class TestJson
    {
        public string description;
        public int port;
        public string[] arguments;
        public string har;
        public int timeout;
        public TestJsonData[] data;
        public TestJsonSteps[] steps;
    }

    class TestJsonData
    {
        public string title;
        public string url;
        public string ga_category;
        public string ga_action;
        public string ga_label;
        public string ym_code;
    }

    class TestJsonSteps
    {
        public string description;
        public string type;
        public string value;
        public string locator;
        public int timeout;
    }
}
