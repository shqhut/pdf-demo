<html>
<head>
    <meta charset="UTF-8"/>
    <style>
        /* 特有css样式自行参考官网 */
        @page {
            size: a4;
            @top-left {
                content: element(header-left);
            }
            @top-center {
                content: element(header-center);
            }
            @top-right {
                content: element(header-right);
            }
            @bottom-center {
                font-size: 14px;
                content: counter(page);
                font-family: 'simsun', serif;
            }
            margin: 50px;
        }

        * {
            margin: 0;
            padding: 0;
            font-family: 'simsun', serif;
        }

        #page-header-left {
            font-size: 10px;
            font-weight: bold;
            position: running(header-left);
        }

        #page-header-center {
            font-size: 10px;
            font-weight: bold;
            position: running(header-center);
        }

        #page-header-right {
            font-size: 10px;
            font-weight: bold;
            position: running(header-right);
        }

        table {
            width: 100%;
            font-size: 14px;
            border-collapse: collapse;
            font-family: 'simsun', serif;
            border-spacing: 0;
            /* The magical table pagination property. */
            /*-fs-table-paginate: paginate;*/
            /* Recommended to avoid leaving thead on a page by itself. */
            -fs-page-break-min-height: 0.5cm;
            border-left: 0.07cm solid black;
            border-right: 0.07cm solid black;
            border-bottom: 0.03cm solid black;
        }

        .checkbox {
            display: inline-block;
            position: relative;
            top: 1px;
            width: 10px;
            height: 10px;
            border: 1px solid black;

        }

        .correct {
            display: inline-block;
            position: relative;
            top: 2px;
            right: 15px;
            width: 7px;
            height: 3px;
            border-left: 1px solid black;
            border-bottom: 1px solid black;
            -webkit-transform: rotate(-45deg);
            transform: rotate(-45deg);
            margin-right: -16px;
        }

        input[type = "checkbox"] {
            width: 10px;
            height: 10px;
            border: 1px solid black;
        }

        tr, thead, tfoot {
            page-break-inside: avoid;
        }

        td, th {
            page-break-inside: avoid;
            font-family: 'simsun', serif;
            padding: 1px;
            border-top: 0.03cm solid black;
            border-left: 0.03cm solid black;
        }


        td:first-child, th:first-child {
            border-left: 0;
        }

        #table-title {
            text-align: center;
            margin-top: 0;
            margin-bottom: 5px;
            font-weight: bold;
        }

    </style>
</head>
<body>
<div class="header-box">
    <div id="page-header-left">
        <span id="page-header-text">编号：<span style="font-weight: normal">${code!''}</span></span>
    </div>
</div>
</body>
</html>