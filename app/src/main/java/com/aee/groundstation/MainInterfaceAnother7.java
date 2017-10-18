package com.aee.groundstation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;
import com.aee.groundstation.activity.messageOfAirlineandAirpoint.Airpoint;
import com.aee.groundstation.activity.messageOfAirlineandAirpoint.InterestPoint;
import com.aee.groundstation.activity.messageOfAirlineandAirpoint.TaskofAirpoint;
import com.aee.groundstation.adapter.MessageAdapter;
import com.aee.groundstation.adapter.RecycleAdapter;
import com.aee.groundstation.operation.DeleteOperation;
import com.aee.groundstation.operation.ScanTaskOperation;
import com.aee.groundstation.operation.XMLOperation;
import com.aee.groundstation.tool.MarkerMessage;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by fuhz on 2017/3/4.
 */
public class MainInterfaceAnother7 extends Activity implements TextureView.SurfaceTextureListener, MKOfflineMapListener {
    private boolean isMapBig=false;
    private boolean isTextureBig=true;
    private RelativeLayout fatherOfMapView;
    private TextureView mAutoFitTextureView;
    private MapView mapView;
    private BaiduMap map;
    private Marker mMarkerA;
    private int screenWidth,screenHeight;
    private static final int PREVIEW_WID = 1920;
    private static final int PREVIEW_HEI = 1080;
    private Camera mCamera;
    private MKOfflineMap mKOfflineMap;
    private ArrayList<MKOLUpdateElement> localMapList=null;
    private MKOLUpdateElement e;
    private RelativeLayout mRelativeLayout;//地图的父布局
    private UiSettings mUiSettings;
    private static final  int CMD_FLYINGSTATUS=2;
    Button btnOfAirline,btnOfZone,btnOfSet;//航点、区域和设置
    LinearLayout layoutOfTaskZone,layoutOfAirpointZone,layoutOfGlobalSetOfAirpoint,
            layoutOfAirpointTaskActionList,layoutOfAdjustAirpointLocation,layoutOfAirpointSetting;
    Button btnOfTaskParameterSet,btnOfAttitudeParameterSet,btnOfAirbornParameterSet
            ,btnOfMountParameterSet,btnOfPressKey,btnOfAlertSet;
    LinearLayout layoutOfTaskParameterSet,layoutOfAttitudeParameterSet,
            layoutOfAirbornParameterSet,layoutOfPressKey,layoutOfAlertSet;
    TextView nameOfTask;
    RecyclerView listviewOfTask;
    RecycleAdapter adapterOfTask;
    Button btnOfDeleteAirpoint, btnOfDownAirpoint;//对航点任务列表执行删除、显示信息操作
    Button btnOfAirpoint,btnOfInterest,btnOfSelect;//航点，兴趣点和选点操作
    RecyclerView listViewOfAirpoint,recyclerviewOfInterest;//显示航点的顺序列表,兴趣点列表
    RecycleAdapter adapterOfAirpoint,adapterOfInterest;//航点顺序列表和兴趣点列表适配器
    Button btnOfAddTask,btnOfDeleteTask,btnOfEditTask;//对任务执行增加、删除、编辑操作
    int flag1=1,flag2=1,flag3=1,flag4=1,flag5=1;//用于显示和隐藏设置布局的标记位
    BitmapDescriptor bdA = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);//HOME点标记
    BitmapDescriptor bdb = BitmapDescriptorFactory.fromResource(R.drawable.icon_markb);//普通航点标记
    BitmapDescriptor bdc = BitmapDescriptorFactory.fromResource(R.drawable.icon_markc);//备用航点标记
    BitmapDescriptor bdi = BitmapDescriptorFactory.fromResource(R.drawable.icon_marki);//兴趣点标记
    BitmapDescriptor bdg = BitmapDescriptorFactory.fromResource(R.drawable.icon_markg);//选点标记

    BitmapDescriptor bde = BitmapDescriptorFactory.fromResource(R.drawable.icon_marke);//区域任务，扫描使用的Marker
    BitmapDescriptor bdf = BitmapDescriptorFactory.fromResource(R.drawable.icon_markf);//区域任务，扫描使用的准Marker

    List<String> listOfTask=new ArrayList<>();//存储所有任务的列表
    List<Airpoint> listOfAirpoint=new ArrayList<>();//存储航点
    List<String> listOfAirpointOperation=new ArrayList<>();//存储航点对应的信息的列表
    List<String> listOfInterestOperation=new ArrayList<>();//存储兴趣点对应的信息的列表
    List<Marker> listOfSelectPoint=new ArrayList<>();//存储选点
    Map<String,List<Airpoint>> hashmap=new HashMap<>();//存储航点任务以及与任务对应的航点
    List<String> listOfZone=new ArrayList<>();//用于存储区域任务的列表
    //姿态参数设置
    private SeekBar seekbarOfZoom,seekbarOfRoll,seekbarOfPitch,seekbarOfThrottle,seekbarOfCameraYaw,seekbarOfCameraPitch;
    private EditText edittextOfZoom,edittextOfRoll,edittextOfPitch,edittextOfThrottle,edittextOfCameraYaw,edittextOfCameraPitch;
    //任务信息
    private TextView featureCode,timeOfFlying,numberOfAirpoint,lengtOfAirLine;//特征码，飞行时间，航点数，航线总长
    //航点地理姿态设置参数
    private SeekBar seekbarOfAltidueheight,seekbarOfGroundHeight,seekbarOfUpSpeed,seekbarOfDownSpeed,seekbarOfFlyingSpeed;
    private TextView textViewOfAltidueheight,textViewOfGroundHeight,textViewOfUpSpeed,textViewOfDownSpeed,textViewOfFlyingSpeed;
    private Spinner spinnerOfYawMode;
    private TextView latitudeOfAirpoint,longitudeOfAirpoint;//航点对应的经纬度
    public int positionOfTasklist=-1;//任务列表使用的position
    public int position2=-1;//航点列表被选中的position
    Overlay overlay;
    List<Airpoint> listOfPreAirpoint=new ArrayList<>();//存储准航点
    int judge=0;//判断拖拽的是航点，准航点,兴趣点 0航点1准航点2兴趣点
    static Airpoint hh;//全局的航点
    SeekBar seekBarOfFlyingheight;//航点飞行高度
    TextView textViewOfFlyingheight;
    Spinner spinnerOfXuanzhuanDirection;
    ArrayAdapter<String> adapterOfXuanzhuanDirection;
    List<String> listOfXuanzhuanDirection=new ArrayList<>();//航点飞行高度
    int idOfSelectedAirpoint=-1;//被选中的航点的ID
    //航点机载设置参数
    TextView valueOfCamerapitch,valueOfCameraroll;
    SeekBar seekbarOfCamerapitch,seekbarOfCameraroll;
    Spinner spinnerOftypeOfCamera,spinnerOfnumberOfCamera,spinnerOfyawmodeOfCamera;
    //航点任务动作添加参数
    SeekBar seekbarOfDelay,seekbarOfActionyaw,seekabrOfActionFlyingheight,seekbarOfActionUpspeed,
            seekbarOfActionDownspeed,seekbarOfActionFlyingspeed,seekbarOfActionTakephotoDelay,seekbarOfActionCameraYaw
            ,seekbarOfActionCameraPitch;
    TextView valueOfDelay,valueOfActionyaw,valueOfActionFlyingheight, valueOfActionUpspeed,valueOfActionDownspeed,
            valueOfActionFlyingspeed,valueOfActionTakephotoDelay,valueOfCameraYaw,valueOfCameraPitch;
    Spinner spinnerOfActionInterest,spinnerOfActionTakeptoto,spinnerOfActionRecordVidio,spinnerOfFormatcamera,
            spinnerOfCameraNumber,spinnerOfCameraInterest,spinnerOfSaving,spinnerOfHook,spinnerOfDiaocang,spinnerOfHanhuaqi,
            spinnerOfSearchLight,spinnerOfJiguang,spinnerOfWirelessRelay;
    List<String> listOfYawMode=new ArrayList<String>();//偏航角模式
    List<String> listOfCameraType=new ArrayList<String>();//相机类型
    List<String> listOfCameraNumber=new ArrayList<String>();//相机编号
    List<String> listOfCameraYaw=new ArrayList<String>();//相机偏航角模式
    List<String> listOfActionInterest=new ArrayList<String>();//动作兴趣点
    List<String> listOfActionTakephoto=new ArrayList<String>();//动作拍照
    List<String> listOfActionRecordVideo=new ArrayList<String>();//动作录像
    List<String> listOfActionCameraFormat=new ArrayList<String>();//动作相机格式化
    List<String> listOfActionCameraNumber=new ArrayList<String>();//动作相机编号
    List<String> listOfActionSaving=new ArrayList<String>();//动作救生投放位置
    List<String> listOfActionHook=new ArrayList<String>();//动作挂钩
    List<String> listOfActionDiaocang=new ArrayList<String>();//动作吊舱
    List<String> listOfActionHanhuaqi=new ArrayList<String>();//动作喊话器
    List<String> listOfActionSearchlight=new ArrayList<String>();//动作探照灯
    List<String> listOfActionJiguang=new ArrayList<String>();//动作激光
    List<String> listOfActionWireless=new ArrayList<String>();//动作无线中继
    List<String> listOfActionCameraInterest=new ArrayList<String>();//动作相机兴趣点
    //航点任务动作添加相关参数
    RecyclerView listviewOfAirpointTaskAction;
    Button btnOfDownTaskAction,btnOfDeleteTaskAction;//执行显示航点任务信息和删除的操作
    List<TaskofAirpoint> listOfAirpointTaskAction1=new ArrayList<>();//航点任务动作列表
    MessageAdapter mMessageAdapter;
    boolean isClick=false;
    int positionOfSelectedTask=-1;//选中的航点任务列表的任务
    //调整航点的经纬度
    Button btnUnit,btnAddLati,btnSubLati,btnAddLongi,btnSubLongi;
    DecimalFormat format=new DecimalFormat("0.000000");//航点经纬度的精度
    DecimalFormat format1=new DecimalFormat("0.0");//偏航，横滚，俯仰的精度
    int flagOfUnit=1;//单位标识
    Double valuetoLatitudeOfAirpoint,valuetoLongitudeOfAirpoint;//航点的经纬度的值
    int distance=0;//航线的长度
    private int btnFlag=0;//0代表航点任务，1代表区域任务
    private int markerFlag=-1;//0代表产生航点，1代表兴趣点，2代表选点
    private int deleteFlag=-1;//0代表选中航点，1代表选中兴趣点
    int fuckposition;//表示任务类在列表中的顺序
    int index;//表示航点信息在列表中的索引
    public List<InterestPoint> listOfPoi=new ArrayList<>();//存储兴趣点的列表
    LinearLayout layoutForTasktype,layoutForMessageOperation,layoutForZonetask;//任务类型布局，信息操作布局，区域任务布局
    Button btnExit,btnSave,btnUpload,btnExecute,btnPauseContinue,btnReturnBack;//退出，保存，上传，执行，停止继续，返航

    Button btnScan,btnEnclosure,btnAdd_Zone,btnDelete_Zone,btnApply_Zone;//区域任务操作（扫描，围栏，增加，删除，应用）
    int isScan=-1;//0表示扫描任务，1表示围栏任务
    ScanTaskOperation scanTaskOperation=new ScanTaskOperation();
    HashMap<String,List<Marker>> mapOfMarker=new HashMap<>();//用以存储和扫描任务对应的Marker的链表
    HashMap<String,List<Marker>> mapOfPreMarker=new HashMap<>();//用以存储和扫描任务对应的准Marker的链表
    RecyclerView rvOfZonetask;//区域任务的RecyclerView
    RecycleAdapter adapterOfZonetask;//区域任务的RecyclerView的适配器
    int positionForZonetask=0;//被选中的任务区域item
    List<String> listOfZonetask=new ArrayList<>();//区域任务的RecyclerView的数据源(扫描和围栏任务)

    //异步消息处理
    private Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case CMD_FLYINGSTATUS:
                    break;
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.maininterface_another);
        // 离线地图初始化，很重要
        mKOfflineMap=new MKOfflineMap();
        mKOfflineMap.init(this);
        localMapList=mKOfflineMap.getAllUpdateInfo();
        e=localMapList.get(0);
        LatLng p = new LatLng(e.geoPt.latitude, e.geoPt.longitude);
        //将变大变小符号和比例尺除去
        mapView = new MapView(this,
                new BaiduMapOptions().zoomControlsEnabled(false).scaleControlEnabled(false).mapStatus(new MapStatus.Builder()
                        .target(p).build()));
        View child = mapView.getChildAt(1);
        // 隐藏百度logo
        if (child instanceof ImageView || child instanceof ZoomControls) {
            child.setVisibility(View.GONE);
        }
        map=mapView.getMap();
        //对map做一些设置，以免影响体验,获取UISetting,控制缩放、旋转、平移、俯视、指南针开启等
        mUiSettings=map.getUiSettings();
        mUiSettings.setZoomGesturesEnabled(true);
        //双击map事件，会Mark一下

        //将百度离线地图动态添加
        mRelativeLayout=(RelativeLayout)findViewById(R.id.fatherOfMapView);
        mRelativeLayout.addView(mapView);

        map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                return false;
            }
            @Override
            public void onMapClick(LatLng arg0) {
                if (!isMapBig) { // 只有地图是小视图的时候点击可以放大
                    changeToBig();
                    isMapBig = true;
                    isTextureBig=false;
                }
            }
        });
        //长按地图生成航点
        map.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                switch (markerFlag){
                    //产生航点
                    case 0:
                        //航点列表为0
                        if(listOfAirpoint.size()==0){
                            OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bdA)
                                    .zIndex(5).draggable(true);
                            mMarkerA = (Marker) (map.addOverlay(ooA));
                            Airpoint airpoint=new Airpoint();
                            airpoint.setMarker(mMarkerA);
                            airpoint.setLatLng(mMarkerA.getPosition());
                            airpoint.setId(1);
                            listOfAirpoint.add(airpoint);
                            listOfAirpointOperation.add(""+1);
                            adapterOfAirpoint.notifyDataSetChanged();
                            showMessage("该航点为"+airpoint.getId()+"\n"+
                                    "经度为"+latLng.longitude+"\n"+
                                    "纬度为"+latLng.latitude);
                        }else {
                            OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bdb)
                                    .zIndex(5).draggable(true);
                            mMarkerA = (Marker) (map.addOverlay(ooA));
                            Airpoint start=listOfAirpoint.get(listOfAirpoint.size()-1);
                            Airpoint airpoint=new Airpoint();
                            airpoint.setMarker(mMarkerA);
                            airpoint.setLatLng(mMarkerA.getPosition());
                            int size=listOfAirpoint.size();
                            airpoint.setId(size+1);
                            listOfAirpoint.add(airpoint);
                            listOfAirpointOperation.add(""+(size+1));
                            adapterOfAirpoint.notifyDataSetChanged();
                            generateHangxian(listOfAirpoint);
                            generatePreAirpoint(start,airpoint,listOfPreAirpoint);
                            showMessage("该航点为"+airpoint.getId()+"\n"+
                                    "经度为"+latLng.longitude+"\n"+
                                    "纬度为"+latLng.latitude);
                        }
                        break;
                    case 1:
                        //增加兴趣点
                        OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bdi)
                                .zIndex(5).draggable(true);
                        Marker mMarkerA = (Marker) (map.addOverlay(ooA));
                        InterestPoint poi=new InterestPoint();
                        int size=listOfPoi.size()+1;
                        poi.setId(size);
                        poi.setLatLng(latLng);
                        poi.setMarker(mMarkerA);
                        listOfInterestOperation.add(""+size);
                        adapterOfInterest.notifyDataSetChanged();
                        listOfPoi.add(poi);
                        break;
                    case 2:
                        //选点,并保存
                        OverlayOptions ooB = new MarkerOptions().position(latLng).icon(bdg)
                                .zIndex(5).draggable(true);
                        Marker mMarkerB = (Marker) (map.addOverlay(ooB));
                        listOfSelectPoint.add(mMarkerB);
                        break;
                    case -1:
                    default:
                        break;
                }
            }
        });
        //标记物拖拽事件，长按标记，然后来回移动
        map.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            List<MarkerMessage> list11=new ArrayList<>();//保存原始的Marker信息
            List<MarkerMessage> list12=new ArrayList<>();//保存原始的准Marker
            String s="";
            public void onMarkerDrag(Marker marker) {
                switch (judge){
                    case 0:
                        tuozhuaiHangdian(listOfAirpoint,marker,listOfPreAirpoint);
                        generateHangxian(listOfAirpoint);
                        break;
                    case 1:
                        tuozhuaiBeiyongHangdianProcess(listOfAirpoint,listOfPreAirpoint,marker);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 100:
                        scanTaskOperation.dragMarker(marker,mapOfMarker.get(s),mapOfPreMarker.get(s),marker.getPosition());
                        overlay=scanTaskOperation.generateScanZone(overlay,mapOfMarker.get(s),map);
                    default:
                        break;
                }
            }
            public void onMarkerDragEnd(Marker marker) {
                switch (judge){
                    //航点
                    case 0:
                        tuozhuaiHangdian(listOfAirpoint,marker,listOfPreAirpoint);
                        generateHangxian(listOfAirpoint);
                        break;
                    //准航点
                    case 1:
                        tuozhuaiBeiyongHangdian(listOfAirpoint,listOfPreAirpoint,marker,bdb,listOfAirpointOperation,adapterOfAirpoint);
                        generateHangxian(listOfAirpoint);
                        break;
                    //兴趣点
                    case 2:
                        Iterator<InterestPoint> it=listOfPoi.iterator();
                        while (it.hasNext()){
                            InterestPoint poi=it.next();
                            if(poi.getMarker().equals(marker)){
                                String s=poi.getLatLng().latitude+" "+poi.getLatLng().longitude;
                                poi.setMarker(marker);
                                poi.setLatLng(marker.getPosition());
                                String ss=poi.getLatLng().latitude+" "+poi.getLatLng().longitude;
                                showMessage(s+"\n"+ss);
                            }
                        }
                        break;
                    //选点
                    case 3:
                        break;
                    case 100:
                        scanTaskOperation.dragMarker(marker,mapOfMarker.get(s),mapOfPreMarker.get(s),marker.getPosition());
                        overlay=scanTaskOperation.generateScanZone(overlay,mapOfMarker.get(s),map);
                        boolean b=scanTaskOperation.isIntersect(mapOfMarker.get(s));
                        if(b){
                            for(int i=0;i<list11.size();i++){
                                MarkerMessage markerMessage=list11.get(i);
                                LatLng latLng=new LatLng(markerMessage.getLatM(),markerMessage.getLonM());
                                Marker marker1=mapOfMarker.get(s).get(i);
                                marker1.setPosition(latLng);
                                mapOfMarker.get(s).set(i,marker1);
                            }
                            for(int i=0;i<mapOfMarker.get(s).size();i++){
                                LatLng latLng1=mapOfMarker.get(s).get(i).getPosition();
                                int size=mapOfMarker.get(s).size()-1;
                                LatLng latLng2;
                                if(i==size){
                                    latLng2=mapOfMarker.get(s).get(0).getPosition();
                                }else {
                                    latLng2=mapOfMarker.get(s).get(i+1).getPosition();
                                }
                                double latitude=(latLng1.latitude+latLng2.latitude)/2;
                                double longitude=(latLng1.longitude+latLng2.longitude)/2;
                                LatLng latLng=new LatLng(latitude,longitude);
                                mapOfPreMarker.get(s).get(i).setPosition(latLng);
                            }
                            overlay=scanTaskOperation.generateScanZone(overlay,mapOfMarker.get("1"),map);
                        }
                        showMessage("有交叉："+b);
                    default:
                        break;
                }
            }
            public void onMarkerDragStart(Marker marker) {
                judge(listOfAirpoint,listOfPreAirpoint,listOfPoi,marker);
                s=listOfZonetask.get(positionForZonetask);
                //将原始的信息保存，此时有一个小的bug,
                //摁下marker时，会有一个向上弹的动作，该marker对应的经纬度已经改变,不再是最原始的
                list11.clear();
                list12.clear();
                for(Marker marker1:mapOfMarker.get(s)){
                    double latitude=marker1.getPosition().latitude;
                    double longitude=marker1.getPosition().longitude;
                    MarkerMessage markerMessage=new MarkerMessage();
                    markerMessage.setLatM(latitude);
                    markerMessage.setLonM(longitude);
                    list11.add(markerMessage);
                }
                for(Marker marker1:mapOfPreMarker.get(s)){
                    double latitude=marker1.getPosition().latitude;
                    double longitude=marker1.getPosition().longitude;
                    MarkerMessage markerMessage=new MarkerMessage();
                    markerMessage.setLatM(latitude);
                    markerMessage.setLonM(longitude);
                    list12.add(markerMessage);
                }
            }
        });
        map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                switch (markerFlag){
                    case 0:
                        Iterator<Airpoint> it=listOfAirpoint.iterator();
                        while (it.hasNext()){
                            Airpoint airpoint=it.next();
                            if(airpoint.getMarker()==marker){
                                //设置一个被选择的航点变量(存储所有设置好的信息，然后赋值给对应的航点)
                                hh=airpoint;
                                idOfSelectedAirpoint=airpoint.getId();
                            }
                        }
                        for(Airpoint hangDianMessage:listOfAirpoint){
                            if(hangDianMessage.getMarker()==marker){
                                layoutOfAdjustAirpointLocation.setVisibility(View.VISIBLE);
                                layoutOfAirpointTaskActionList.setVisibility(View.VISIBLE);
                                layoutOfAirpointSetting.setVisibility(View.VISIBLE);
                                //重新进入一个航点后，其对应的航点任务列表必须更新，代码如下
                                listOfAirpointTaskAction1.clear();
                                for(TaskofAirpoint s:hh.getListOfairpointAction1()){listOfAirpointTaskAction1.add(s);}
                                mMessageAdapter.notifyDataSetChanged();
                            }
                        }
                        for(Airpoint hangDianMessage:listOfPreAirpoint){
                            if(hangDianMessage.getMarker()==marker){
                                layoutOfAdjustAirpointLocation.setVisibility(View.GONE);
                                layoutOfAirpointTaskActionList.setVisibility(View.GONE);
                                layoutOfAirpointSetting.setVisibility(View.GONE);
                            }
                        }
                        featureCode.setText(""+hh.getId());//特征码
                        distance=0;
                        for(int i=0;i<listOfAirpoint.size()-1;i++){
                            distance+=(int) DistanceUtil.getDistance(listOfAirpoint.get(i).getLatLng(), listOfAirpoint.get(i+1).getLatLng());
                        }
                        timeOfFlying.setText(""+distance+"m(未除速度)");//飞行时间(暂且显示路径长度)
                        numberOfAirpoint.setText(""+listOfAirpoint.size());//航点总数
                        lengtOfAirLine.setText(""+distance+"m");//路线长度
                        //设置航点的飞行高度和旋转方向
                        seekBarOfFlyingheight=(SeekBar) findViewById(R.id.seekbar_height_of_flying);
                        textViewOfFlyingheight=(TextView)findViewById(R.id.value_of_height_of_flying);
                        textViewOfFlyingheight.setText(""+hh.getHeightOfFlying()+"m");
                        seekBarOfFlyingheight.setProgress(hh.getHeightOfFlying());
                        seekBarOfFlyingheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                textViewOfFlyingheight.setText(""+progress+"m");
                                hh.setHeightOfFlying(progress);
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });
                        spinnerOfXuanzhuanDirection=(Spinner)findViewById(R.id.spinner_of_direction_rotation);
                        String[] s=getResources().getStringArray(R.array.direction_rotation);
                        for(String ss:s){listOfXuanzhuanDirection.add(ss);}
                        adapterOfXuanzhuanDirection=new ArrayAdapter<String>(MainInterfaceAnother7.this,android.R.layout.simple_spinner_item,listOfXuanzhuanDirection);
                        adapterOfXuanzhuanDirection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerOfXuanzhuanDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                hh.setRotationDirection(listOfXuanzhuanDirection.get(position));
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //航点地理姿态设置
                        //海拔
                        textViewOfAltidueheight.setText(""+hh.getHeightOfAltitudeFly()+"m");//初始化海拔高度
                        seekbarOfAltidueheight.setProgress(hh.getHeightOfAltitudeFly());
                        seekbarOfAltidueheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                textViewOfAltidueheight.setText(""+seekBar.getProgress()+"m");
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                textViewOfAltidueheight.setText(""+seekBar.getProgress()+"m");
                                hh.setHeightOfAltitudeFly(seekBar.getProgress());
                            }
                        });
                        //地面
                        textViewOfGroundHeight.setText(""+hh.getHeightOfGroundFly()+"m");
                        seekbarOfGroundHeight.setProgress(hh.getHeightOfGroundFly());
                        seekbarOfGroundHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                textViewOfGroundHeight.setText(""+seekBar.getProgress()+"m");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                textViewOfGroundHeight.setText(""+seekBar.getProgress()+"m");
                                hh.setHeightOfGroundFly(seekBar.getProgress());
                            }
                        });
                        //上升速度
                        textViewOfUpSpeed.setText(""+hh.getSpeedOfUp()+"m/s");
                        seekbarOfUpSpeed.setProgress(hh.getSpeedOfUp());
                        seekbarOfUpSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                textViewOfUpSpeed.setText(""+seekBar.getProgress()+"m/s");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                textViewOfUpSpeed.setText(""+seekBar.getProgress()+"m/s");
                                hh.setSpeedOfUp(seekBar.getProgress());
                            }
                        });
                        //下降速度
                        textViewOfDownSpeed.setText(""+hh.getSpeedOfDown()+"m/s");
                        seekbarOfDownSpeed.setProgress(hh.getSpeedOfDown());
                        seekbarOfDownSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                textViewOfDownSpeed.setText(""+seekBar.getProgress()+"m/s");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                textViewOfDownSpeed.setText(""+seekBar.getProgress()+"m/s");
                                hh.setSpeedOfDown(seekBar.getProgress());
                            }
                        });
                        //飞行速度
                        textViewOfFlyingSpeed.setText(""+hh.getSpeedOfFlying()+"m/s");
                        seekbarOfFlyingSpeed.setProgress(hh.getSpeedOfFlying());
                        seekbarOfFlyingSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                textViewOfFlyingSpeed.setText(""+seekBar.getProgress()+"m/s");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                textViewOfFlyingSpeed.setText(""+seekBar.getProgress()+"m/s");
                                hh.setSpeedOfFlying(seekBar.getProgress());
                            }
                        });
                        //偏航角模式
                        String[] s1=getResources().getStringArray(R.array.yaw_angle_mode);
                        for(String ss:s1){listOfYawMode.add(ss);}
                        spinnerOfYawMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                hh.setModeOfYaw(listOfYawMode.get(position));
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //(航点)经纬度的值
                        latitudeOfAirpoint.setText(""+hh.getLatLng().latitude);
                        longitudeOfAirpoint.setText(""+hh.getLatLng().longitude);
                        //航点机载设置
                        //相机俯仰和横滚角度
                        valueOfCamerapitch.setText(""+hh.getPitchOfCamera());
                        valueOfCameraroll.setText(""+hh.getRollOfCamera());
                        seekbarOfCamerapitch.setProgress(hh.getPitchOfCamera());
                        seekbarOfCameraroll.setProgress(hh.getRollOfCamera());
                        seekbarOfCamerapitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfCamerapitch.setText(""+seekBar.getProgress());
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                valueOfCamerapitch.setText(""+seekBar.getProgress());
                                hh.setPitchOfCamera(seekBar.getProgress());
                            }
                        });
                        seekbarOfCameraroll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfCameraroll.setText(""+seekBar.getProgress());
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                valueOfCameraroll.setText(""+seekBar.getProgress());
                                hh.setRollOfCamera(seekBar.getProgress());
                            }
                        });
                        //相机类型
                        String[] s2=getResources().getStringArray(R.array.camera_type);
                        for(String ss:s2){listOfCameraType.add(ss);}
                        spinnerOftypeOfCamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                hh.setTypeOfCamera(listOfCameraType.get(position));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //相机编号
                        String[] s3=getResources().getStringArray(R.array.camera_serial_number);
                        for(String ss:s3){listOfCameraNumber.add(ss);}
                        spinnerOfnumberOfCamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                hh.setNumberOfCamera(listOfCameraNumber.get(position));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        String[] s4=getResources().getStringArray(R.array.camera_yaw_angle_mode);
                        for(String ss:s4){listOfCameraYaw.add(ss);}
                        spinnerOfyawmodeOfCamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                hh.setModeOfCameraYaw(listOfCameraYaw.get(position));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //航点动作添加列表
                        //延时
                        seekbarOfDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfDelay.setText(seekBar.getProgress()+"s");
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {}
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=1;
                                String message="延时";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //偏航角
                        seekbarOfActionyaw.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfActionyaw.setText(""+seekBar.getProgress());
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=2;
                                String message="偏航角";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //兴趣点
                        String[] s5=getResources().getStringArray(R.array.action_interest);
                        for(String ss:s5){listOfActionInterest.add(ss);}
                        spinnerOfActionInterest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=3;
                                    String message="兴趣点";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {}
                        });
                        //动作飞行高度
                        seekabrOfActionFlyingheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfActionFlyingheight.setText(seekBar.getProgress()+"m");
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {}
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=4;
                                String message="飞行高度";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //动作上升速度
                        seekbarOfActionUpspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfActionUpspeed.setText(seekBar.getProgress()+"m/s");
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=5;
                                String message="上升速度";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);



                            }
                        });
                        //动作下降速度
                        seekbarOfActionDownspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfActionDownspeed.setText(seekBar.getProgress()+"m/s");
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=6;
                                String message="下降速度";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //动作飞行速度
                        seekbarOfActionFlyingspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfActionFlyingspeed.setText(seekBar.getProgress()+"m/s");
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=7;
                                String message="飞行速度";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //动作拍照
                        String[] s6=getResources().getStringArray(R.array.action_takephoto);
                        for(String ss:s6){listOfActionTakephoto.add(ss);}
                        spinnerOfActionTakeptoto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=8;
                                    String message="拍照";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //动作录像
                        String[] s7=getResources().getStringArray(R.array.action_recordvideo);
                        for(String ss:s7){listOfActionRecordVideo.add(ss);}
                        spinnerOfActionRecordVidio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=9;
                                    String message="录像";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //相机自动间隔延时拍照
                        seekbarOfActionTakephotoDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfActionTakephotoDelay.setText(seekBar.getProgress()+"m/s");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=10;
                                String message="延时拍照";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //相机偏航角
                        seekbarOfActionCameraYaw.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfCameraYaw.setText(seekBar.getProgress()+"");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=11;
                                String message="相机偏航";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //相机俯仰
                        seekbarOfActionCameraPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                valueOfCameraPitch.setText(seekBar.getProgress()+"");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }
                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int sequence=12;
                                String message="相机俯仰";
                                int parameter=seekBar.getProgress();
                                generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                            }
                        });
                        //相机格式化
                        String[] s8=getResources().getStringArray(R.array.action_cameraformat);
                        for(String ss:s8){listOfActionCameraFormat.add(ss);}
                        spinnerOfFormatcamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=13;
                                    String message="相机格式化";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //相机编号
                        String[] s9=getResources().getStringArray(R.array.action_cameranumber);
                        for(String ss:s9){listOfActionCameraNumber.add(ss);}
                        spinnerOfCameraNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=14;
                                    String message="相机编号";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //相机兴趣点
                        String[] s10=getResources().getStringArray(R.array.action_camerainterest);
                        for(String ss:s10){listOfActionCameraInterest.add(ss);}
                        spinnerOfCameraInterest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=15;
                                    String message="相机兴趣点";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //救生投放
                        String[] s11=getResources().getStringArray(R.array.action_putdownlocation);
                        for(String ss:s11){listOfActionSaving.add(ss);}
                        spinnerOfSaving.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=16;
                                    String message="投放点";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //挂钩
                        String[] s12=getResources().getStringArray(R.array.action_hook);
                        for(String ss:s12){listOfActionHook.add(ss);}
                        spinnerOfHook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=17;
                                    String message="挂钩";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //吊舱
                        String[] s13=getResources().getStringArray(R.array.action_diaocang);
                        for(String ss:s13){listOfActionDiaocang.add(ss);}
                        spinnerOfDiaocang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=18;
                                    String message="吊舱";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //喊话器
                        String[] s14=getResources().getStringArray(R.array.action_hanhuaqi);
                        for(String ss:s14){listOfActionHanhuaqi.add(ss);}
                        spinnerOfHanhuaqi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=19;
                                    String message="喊话器";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //探照灯
                        String[] s15=getResources().getStringArray(R.array.action_searchlight);
                        for(String ss:s15){listOfActionSearchlight.add(ss);}
                        spinnerOfSearchLight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=20;
                                    String message="探照灯";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //激光
                        String[] s16=getResources().getStringArray(R.array.action_jiguangzhimang);
                        for(String ss:s16){listOfActionJiguang.add(ss);}
                        spinnerOfJiguang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=21;
                                    String message="激光";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //无线中继
                        String[] s17=getResources().getStringArray(R.array.action_wirelessrelay);
                        for(String ss:s17){listOfActionWireless.add(ss);}
                        spinnerOfWirelessRelay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position!=0){
                                    int sequence=22;
                                    String message="无线中继";
                                    int parameter;
                                    if(position==1){
                                        parameter=0;
                                    }else {
                                        parameter=1;
                                    }
                                    generateTaskOfAirpoint1(sequence,parameter,message,hh,listOfAirpointTaskAction1,mMessageAdapter);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //对航点任务列表进行操作
                        btnOfDownTaskAction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String s="";
                                for(TaskofAirpoint taskofAirpoint:hh.getListOfairpointAction1()){
                                    s+=taskofAirpoint.getMessage()+taskofAirpoint.getParameter()+"\n";
                                }
                                showMessage("第"+hh.getId()+"航点的任务为"+"\n"+s);
                            }
                        });
                        btnOfDeleteTaskAction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new DeleteOperation().deleteTaskOfAirpoint(positionOfSelectedTask,listOfAirpointTaskAction1,
                                        mMessageAdapter,hh);
                                showMessage("第 "+hh.getId()+"个航点任务个数为 "+hh.getListOfairpointAction1().size());
                            }
                        });
                        //调整航点的经纬度
                        //单位
                        valuetoLatitudeOfAirpoint=Double.parseDouble(latitudeOfAirpoint.getText().toString());
                        valuetoLongitudeOfAirpoint=Double.parseDouble(longitudeOfAirpoint.getText().toString());
                        btnUnit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String s=btnUnit.getText().toString();
                                if(s.equals("1")){
                                    btnUnit.setText("10");
                                    flagOfUnit=10;
                                }else if(s.equals("10")){
                                    btnUnit.setText("100");
                                    flagOfUnit=100;
                                }else if(s.equals("100")){
                                    btnUnit.setText("1");
                                    flagOfUnit=1;
                                }
                            }
                        });
                        //纬度加
                        btnAddLati.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(flagOfUnit==1){
                                    valuetoLatitudeOfAirpoint+=0.000001;
                                }else if(flagOfUnit==10){
                                    valuetoLatitudeOfAirpoint+=0.00001;
                                }else if(flagOfUnit==100){
                                    valuetoLatitudeOfAirpoint+=0.0001;
                                }
                                latitudeOfAirpoint.setText(format.format(valuetoLatitudeOfAirpoint));
                                adjustLocation(hh,valuetoLatitudeOfAirpoint,valuetoLongitudeOfAirpoint);
                            }
                        });
                        //纬度减
                        btnSubLati.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(flagOfUnit==1){
                                    valuetoLatitudeOfAirpoint-=0.000001;
                                }else if(flagOfUnit==10){
                                    valuetoLatitudeOfAirpoint-=0.00001;
                                }else if(flagOfUnit==100){
                                    valuetoLatitudeOfAirpoint-=0.0001;
                                }
                                latitudeOfAirpoint.setText(format.format(valuetoLatitudeOfAirpoint));
                                adjustLocation(hh,valuetoLatitudeOfAirpoint,valuetoLongitudeOfAirpoint);
                            }
                        });
                        //经度加
                        btnAddLongi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(flagOfUnit==1){
                                    valuetoLongitudeOfAirpoint+=0.000001;
                                }else if(flagOfUnit==10){
                                    valuetoLongitudeOfAirpoint+=0.00001;
                                }else if(flagOfUnit==100){
                                    valuetoLongitudeOfAirpoint+=0.0001;
                                }
                                longitudeOfAirpoint.setText(format.format(valuetoLongitudeOfAirpoint));
                                adjustLocation(hh,valuetoLatitudeOfAirpoint,valuetoLongitudeOfAirpoint);
                            }
                        });
                        //经度减
                        btnSubLongi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(flagOfUnit==1){
                                    valuetoLongitudeOfAirpoint-=0.000001;
                                }else if(flagOfUnit==10){
                                    valuetoLongitudeOfAirpoint-=0.00001;
                                }else if(flagOfUnit==100){
                                    valuetoLongitudeOfAirpoint-=0.0001;
                                }
                                longitudeOfAirpoint.setText(format.format(valuetoLongitudeOfAirpoint));
                                adjustLocation(hh,valuetoLatitudeOfAirpoint,valuetoLongitudeOfAirpoint);
                            }
                        });
                        //将设置好的航点替换掉对应位置的航点
                        listOfAirpoint.set(idOfSelectedAirpoint-1,hh);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case -1:
                    default:
                        break;
                }
                return false;
            }
        });
        init();
        initData();
        new Thread(new MyThread()).start();
    }
    private void init(){
        mAutoFitTextureView=(TextureView)findViewById(R.id.mAutoFitTextureView);
        //mAutoFitTextureView.setAspectRatio(PREVIEW_WID, PREVIEW_HEI);
        mAutoFitTextureView.setSurfaceTextureListener(this);
        fatherOfMapView=(RelativeLayout)findViewById(R.id.fatherOfMapView);
        mAutoFitTextureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTextureBig){
                    changeToBigAgain();
                    isTextureBig=true;
                    isMapBig=false;
                }
            }
        });
        initAll();
        showLayout();
        showOrHide();
        messageOperation();
        operationTaskList();
        operationAirpointList();
    }
    private void changeToBig(){
        ValueAnimator va_map_horizontal = ValueAnimator.ofInt(
                400, screenWidth);
        ValueAnimator va_map_vertical = ValueAnimator.ofInt(
                200, screenHeight);
        mAutoFitTextureView.bringToFront();
        va_map_horizontal.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dx = (Integer) animation.getAnimatedValue();
                //获取地图对应的布局
                ViewGroup.LayoutParams params = fatherOfMapView.getLayoutParams();
                params.width = dx;
                fatherOfMapView.setLayoutParams(params);
            }
        });

        va_map_vertical.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dy = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = fatherOfMapView.getLayoutParams();
                params.height = dy;
                fatherOfMapView.setLayoutParams(params);
            }
        });

        va_map_vertical.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {
                // 地图放大动画结束后缩小SurfaceView
                ViewGroup.LayoutParams params = mAutoFitTextureView.getLayoutParams();
                params.width = 400;
                params.height = 200;
                mAutoFitTextureView.setLayoutParams(params);
                mAutoFitTextureView.bringToFront();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        va_map_horizontal.setDuration(500);
        va_map_vertical.setDuration(500);
        va_map_vertical.start();
        va_map_horizontal.start();
    }

    private void changeToBigAgain(){
        ValueAnimator va_map_horizontal = ValueAnimator.ofInt(
                300, screenWidth);
        ValueAnimator va_map_vertical = ValueAnimator.ofInt(
                300, screenHeight);
        fatherOfMapView.bringToFront();
        va_map_horizontal.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dx = (Integer) animation.getAnimatedValue();
                //获取地图对应的布局
                ViewGroup.LayoutParams params = mAutoFitTextureView.getLayoutParams();
                params.width = dx;
                mAutoFitTextureView.setLayoutParams(params);
            }
        });

        va_map_vertical.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dy = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = mAutoFitTextureView.getLayoutParams();
                params.height = dy;
                mAutoFitTextureView.setLayoutParams(params);
            }
        });
        va_map_vertical.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup.LayoutParams params = fatherOfMapView.getLayoutParams();
//                params.width = 300;
//                params.height = 300;
                params.width = 400;
                params.height = 200;
                fatherOfMapView.setLayoutParams(params);
                fatherOfMapView.bringToFront();
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

        va_map_horizontal.setDuration(500);
        va_map_vertical.setDuration(500);
        va_map_vertical.start();
        va_map_horizontal.start();
    }
    //初始化数据
    private void initData() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }
    //显示实时视频
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        if (surfaceTexture != null) {
            try {
                openCamera(surfaceTexture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        releaseCamera();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
    private void openCamera(SurfaceTexture texture) throws IOException {
        if (mCamera == null) {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters();
            mParameters.setPreviewSize(PREVIEW_WID, PREVIEW_HEI);
            mCamera.setParameters(mParameters);
            mCamera.setPreviewTexture(texture);
            mCamera.startPreview();
        }
    }
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
    //显示离线地图的监听器
    public void onGetOfflineMapState(int type, int state) {
        switch (type) {
            case MKOfflineMap.TYPE_DOWNLOAD_UPDATE: {
                MKOLUpdateElement update = mKOfflineMap.getUpdateInfo(state);
            }
            break;
            case MKOfflineMap.TYPE_NEW_OFFLINE:
                Toast.makeText(MainInterfaceAnother7.this,"离线地图加载成功",Toast.LENGTH_SHORT).show();
                // 有新离线地图安装
                break;
            case MKOfflineMap.TYPE_VER_UPDATE:
                // 版本更新提示
                break;
        }
    }
    /*
    * 显示或隐藏布局
    * */
    public void showLayout(){
        //切换到航点任务列表
        btnOfAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfTask.setText("航点任务");
                if(layoutOfTaskZone.getVisibility()==View.GONE){
                    layoutOfTaskZone.setVisibility(View.VISIBLE);
                }
                if(btnFlag!=0){
                    listOfTask.clear();
                    for(String s:hashmap.keySet()){listOfTask.add(s);}
                    adapterOfTask.notifyDataSetChanged();
                }
                btnFlag=0;//航点任务标记为为0
                positionOfTasklist=-1;
            }
        });
        //切换到区域任务列表
        btnOfZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfTask.setText("区域任务");
                if(layoutOfTaskZone.getVisibility()==View.GONE){
                    layoutOfTaskZone.setVisibility(View.VISIBLE);
                }
                if(btnFlag!=1){
                    listOfTask.clear();
                    for(String s:listOfZone){listOfTask.add(s);}
                    adapterOfTask.notifyDataSetChanged();
                }
                btnFlag=1;
                positionOfTasklist=-1;
            }
        });
        //显示隐藏全局设置列表
        btnOfSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutOfGlobalSetOfAirpoint.getVisibility()==View.GONE){
                    layoutOfGlobalSetOfAirpoint.setVisibility(View.VISIBLE);
                }else {
                    layoutOfGlobalSetOfAirpoint.setVisibility(View.GONE);
                }
            }
        });

    }
    //显示或者隐藏全局设置布局
    private void showOrHide(){
        btnOfTaskParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1==1){
                    layoutOfTaskParameterSet.setVisibility(View.VISIBLE);
                    flag1=0;
                }else if(flag1==0){
                    layoutOfTaskParameterSet.setVisibility(View.GONE);
                    flag1=1;
                }
            }
        });
        btnOfAttitudeParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==1){
                    layoutOfAttitudeParameterSet.setVisibility(View.VISIBLE);
                    flag2=0;
                }else if(flag2==0){
                    layoutOfAttitudeParameterSet.setVisibility(View.GONE);
                    flag2=1;
                }
            }
        });
        btnOfAirbornParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag3==1){
                    layoutOfAirbornParameterSet.setVisibility(View.VISIBLE);
                    flag3=0;
                }else if(flag3==0){
                    layoutOfAirbornParameterSet.setVisibility(View.GONE);
                    flag3=1;
                }
            }
        });
        btnOfPressKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag4==1){
                    layoutOfPressKey.setVisibility(View.VISIBLE);
                    flag4=0;
                }else if(flag4==0){
                    layoutOfPressKey.setVisibility(View.GONE);
                    flag4=1;
                }
            }
        });
        btnOfMountParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnOfAlertSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag5==1){
                    layoutOfAlertSet.setVisibility(View.VISIBLE);
                    flag5=0;
                }else if(flag5==0){
                    layoutOfAlertSet.setVisibility(View.GONE);
                    flag5=1;
                }
            }
        });
    }

    //任务列表操作
    private void operationTaskList(){
        btnOfAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(btnFlag){
                    //添加航点任务
                    case 0:
                        int size=listOfTask.size()+1;
                        listOfTask.add(""+size);
                        hashmap.put(""+size,new ArrayList<Airpoint>());
                        adapterOfTask.notifyDataSetChanged();
                        break;
                    //添加区域任务
                    case 1:
                        int size1=listOfZone.size()+1;
                        listOfTask.add(""+size1);
                        listOfZone.add(""+size1);
                        adapterOfTask.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        });
        btnOfDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(positionOfTasklist!=-1){
                    switch(btnFlag){
                        case 0:
                            //获取选择的item的值，将其移除
                            String s=listOfTask.get(positionOfTasklist);
                            listOfTask.remove(s);
                            adapterOfTask.notifyDataSetChanged();
                            showMessage("还有"+listOfTask.size()+"个航点任务");
                            //获取与该item对应的航点列表,将里面的航点删除
                            for(Airpoint airpoint:hashmap.get(s)){airpoint.getMarker().remove();}
                            hashmap.get(s).clear();
                            hashmap.remove(s);
                            //同时删除与之对应的准航点
                            for(Airpoint airpoint:listOfPreAirpoint){airpoint.getMarker().remove();}
                            listOfPreAirpoint.clear();
                            if(overlay!=null){
                                overlay.remove();
                            }
                            //更新航点操作列表
                            listOfAirpointOperation.clear();
                            adapterOfAirpoint.notifyDataSetChanged();
                            //更新任务信息和Map信息
                            for(String s1:listOfTask){
                                //获取每一个item对应的position
                                int position1=listOfTask.indexOf(s1);
                                //如果position1大于或者等于positionOfTasklist，执行操作
                                if(position1>=positionOfTasklist){
                                    List<Airpoint> list=hashmap.get(s1);
                                    hashmap.remove(s1);
                                    hashmap.put(""+(position1+1),list);
                                    listOfTask.set(position1,""+(position1+1));
                                }
                            }
                            adapterOfTask.notifyDataSetChanged();
                            //赋值，要不容易出现越界异常
                            positionOfTasklist=-1;
                            break;
                        //移除选中的区域任务
                        case 1:
                            listOfTask.remove(positionOfTasklist);
                            listOfZone.remove(positionOfTasklist);
                            adapterOfTask.notifyDataSetChanged();
                            positionOfTasklist=-1;
                            break;
                        default:
                            break;
                    }
                }else{
                    showMessage("请选择航点任务再删除");
                }
            }
        });
        btnOfEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(positionOfTasklist!=-1){
                    //任务类型布局隐藏
                    layoutForTasktype.setVisibility(View.GONE);
                    //显示信息操作布局
                    layoutForMessageOperation.setVisibility(View.VISIBLE);
                    switch(btnFlag){
                        case 0:
                            String s=listOfTask.get(positionOfTasklist);
                            listOfAirpoint=hashmap.get(s);
                            layoutOfTaskZone.setVisibility(View.GONE);
                            layoutOfAirpointZone.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            layoutOfTaskZone.setVisibility(View.GONE);
                            layoutForZonetask.setVisibility(View.VISIBLE);
                            zoneOperation();
                            break;
                        default:
                            break;
                    }

                }
            }
        });
    }
    //航点列表操作
    private void operationAirpointList(){
        //删除选中的一个航点
        btnOfDeleteAirpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (deleteFlag){
                    case 0:
                        deleteAirpoint(listOfAirpointOperation,position2,adapterOfAirpoint,listOfAirpoint,listOfPreAirpoint);
                        position2=-1;
                        deleteFlag=-1;
                        break;
                    case 1:
                        deleteInterest(position2,listOfInterestOperation,adapterOfInterest,listOfPoi);
                        position2=-1;
                        deleteFlag=-1;
                        break;
                }
            }
        });
        //显示航点的ID
        btnOfDownAirpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="";
                for(Airpoint airpoint:listOfAirpoint){s+=airpoint.getId()+" ";}
                showMessage(s);
            }
        });
        //设定标示，进行航点操作
        btnOfAirpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markerFlag=0;
            }
        });
        //设定标示，进行兴趣点操作
        btnOfInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markerFlag=1;
            }
        });
        //设定标示，进行选点操作
        btnOfSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markerFlag=2;
            }
        });
    }
    //删除航点操作
    //listOfInteger为航点顺序列表,position为选中的item序号，adapter与listOfInteger对应，list为存储航点的列表，beiyongHangdian为存储准航点的列表
    private void deleteAirpoint(List<String> listOfInteger,int position,RecycleAdapter adapter,List<Airpoint> list,List<Airpoint> beiyongHangdian){
        //删除marker对应的航点
        int id=0;
        Iterator<Airpoint> iterator=list.iterator();
        while (iterator.hasNext()){
            Airpoint hangDianMessage=iterator.next();
            if(hangDianMessage.getId()==(position+1)){
                id=hangDianMessage.getId();
                index=list.indexOf(hangDianMessage);
                hangDianMessage.getMarker().remove();
                iterator.remove();
            }
        }
        //更新航点的ID信息
        Iterator<Airpoint> iterator1=list.iterator();
        while (iterator1.hasNext()){
            Airpoint hangDianMessage=iterator1.next();
            if(hangDianMessage.getId()>id){
                int index1=list.indexOf(hangDianMessage);
                if(hangDianMessage.getId()-1==1){
                    hangDianMessage.getMarker().setIcon(bdA);
                }
                hangDianMessage.setId(hangDianMessage.getId()-1);
                list.set(index1,hangDianMessage);
            }
        }
        //更新航点顺序列表
        listOfInteger.clear();
        for(Airpoint airpoint:list){
            listOfInteger.add(""+airpoint.getId());
        }
        adapter.notifyDataSetChanged();
        //更新航线
        generateHangxian(list);
        //更新备用航点
        //如果删除的是第一个航点
        if(0==index){
            if(beiyongHangdian.size()==0){

            }else {
                Airpoint hangDianMessage=beiyongHangdian.get(0);
                hangDianMessage.getMarker().remove();
                beiyongHangdian.remove(0);
            }
        }else if(index==list.size()){
            Airpoint hangDianMessage=beiyongHangdian.get(index-1);
            hangDianMessage.getMarker().remove();
            beiyongHangdian.remove(index-1);
        }else {
            Airpoint hangDianMessage=beiyongHangdian.get(index-1);
            hangDianMessage.getMarker().remove();
            beiyongHangdian.remove(index-1);
            Airpoint hangDianMessage1=beiyongHangdian.get(index-1);
            hangDianMessage1.getMarker().remove();
            beiyongHangdian.remove(index-1);
            Airpoint h1=list.get(index-1);
            Airpoint h2=list.get(index);
            Airpoint hangDianMessage2=new Airpoint();
            double latitude=h1.getLatLng().latitude+h2.getLatLng().latitude;
            double longitude=h1.getLatLng().longitude+h2.getLatLng().longitude;
            LatLng latLng=new LatLng(latitude/2,longitude/2);
            hangDianMessage2.setLatLng(latLng);
            OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bdc)
                    .zIndex(9).draggable(true);
            mMarkerA = (Marker) (map.addOverlay(ooA));
            hangDianMessage2.setMarker(mMarkerA);
            //考虑加行点
            beiyongHangdian.add(index-1,hangDianMessage2);
        }
        showMessage("第"+id+"个航点删除"+"\n"+
                "航点还有"+list.size()+"个"+"\n"+
                "剩余的备用航点有"+beiyongHangdian.size());
    }
    //生成航线
    private void generateHangxian(List<Airpoint> list){
        if(list.size()>=2){
            if (overlay != null){
                overlay.remove();
            }
            List<LatLng> pts = new ArrayList<LatLng>();
            for(Airpoint airpoint:list){
                pts.add(airpoint.getLatLng());
            }
            OverlayOptions ooPolyline = new PolylineOptions().width(5)
                    .color(0xAAFF0000).points(pts);
            overlay = map.addOverlay(ooPolyline);
        }else{
            if (overlay != null){
                overlay.remove();
            }
        }
    }
    //生成的点还不是航点，经过拖拽后才会成为航点(高级航线的时候应用)
    private void generatePreAirpoint(Airpoint start,Airpoint end,List<Airpoint> listOfpreAirpoint){
        Airpoint airpoint=new Airpoint();
        double latitude=start.getLatLng().latitude+end.getLatLng().latitude;
        double longitude=start.getLatLng().longitude+end.getLatLng().longitude;
        LatLng latLng=new LatLng(latitude/2,longitude/2);
        airpoint.setLatLng(latLng);
        OverlayOptions ooA = new MarkerOptions().position(airpoint.getLatLng()).icon(bdc)
                .zIndex(9).draggable(true);
        mMarkerA = (Marker) (map.addOverlay(ooA));
        airpoint.setMarker(mMarkerA);
        listOfpreAirpoint.add(airpoint);
        showMessage("备用航点数量为"+listOfpreAirpoint.size());
    }
    //判断被拖拽的点代表什么，0代表航点1代表准航点,2代表兴趣点3代表选点
    private void judge(List<Airpoint> list,List<Airpoint> beiyongHangdian,List<InterestPoint> listOfPoi,Marker marker){
        for(Airpoint airpoint:list){
            if(airpoint.getMarker()==marker){
                judge=0;
            }
        }
        for(Airpoint airpoint:beiyongHangdian){
            if(airpoint.getMarker()==marker){
                judge=1;
            }
        }
        for(InterestPoint poi:listOfPoi){
            if(poi.getMarker()==marker){
                judge=2;
            }
        }
        for(Marker marker1:listOfSelectPoint){
            if(marker1.equals(marker)){
                judge=3;
            }
        }
        judge=100;
    }
    //拖拽一个航点，相应的一些备用航点会改变自己原来的位置(功能可以使用)
    private void tuozhuaiHangdian(List<Airpoint> list,Marker marker,List<Airpoint> beiyongList){
        Iterator<Airpoint> iterator=list.iterator();
        while (iterator.hasNext()){
            Airpoint hangDianMessage=iterator.next();
            if(hangDianMessage.getMarker()==marker){
                int index=list.indexOf(hangDianMessage);
                hangDianMessage.setLatLng(marker.getPosition());
                list.set(index,hangDianMessage);
                if(list.size()>1){
                    //如果拖拽第一个点
                    if(index==0){
                        Airpoint h2=list.get(1);
                        LatLng latLng=new LatLng((hangDianMessage.getLatLng().latitude+h2.getLatLng().latitude)/2,
                                (hangDianMessage.getLatLng().longitude+h2.getLatLng().longitude)/2);
                        Airpoint chuli1=beiyongList.get(0);
                        Marker marker1=chuli1.getMarker();
                        chuli1.setLatLng(latLng);
                        marker1.setPosition(latLng);
                        beiyongList.set(0,chuli1);
                    }else if(index>0&&index<list.size()-1){
                        //获取拖拽航点的前一个点和后一个点
                        Airpoint h1=list.get(index-1);
                        Airpoint h2=list.get(index+1);
                        //分别求取与前一个点和后一个点的中间经纬度
                        LatLng latLng1=new LatLng((hangDianMessage.getLatLng().latitude+h1.getLatLng().latitude)/2,
                                (hangDianMessage.getLatLng().longitude+h1.getLatLng().longitude)/2);
                        LatLng latLng2=new LatLng((hangDianMessage.getLatLng().latitude+h2.getLatLng().latitude)/2,
                                (hangDianMessage.getLatLng().longitude+h2.getLatLng().longitude)/2);
                        //获取之前与之对应的备用航点
                        Airpoint chuli1=beiyongList.get(index-1);
                        Airpoint chuli2=beiyongList.get(index);
                        //获取与前一个点对应的备用航点的信息，修改成新的经纬度
                        Marker marker1=chuli1.getMarker();
                        chuli1.setLatLng(latLng1);
                        marker1.setPosition(latLng1);
                        //获取与后一个点对应的备用航点的信息，修改成新的经纬度
                        Marker marker2=chuli2.getMarker();
                        chuli1.setLatLng(latLng2);
                        marker2.setPosition(latLng2);
                        //更新
                        beiyongList.set(index-1,chuli1);
                        beiyongList.set(index,chuli2);
                        //拖拽最后一个点
                    }else if(index==list.size()-1){
                        Airpoint h2=list.get(list.size()-2);
                        LatLng latLng=new LatLng((hangDianMessage.getLatLng().latitude+h2.getLatLng().latitude)/2,
                                (hangDianMessage.getLatLng().longitude+h2.getLatLng().longitude)/2);
                        int indexOfBeiyong=list.size()-2;
                        Airpoint chuli1=beiyongList.get(indexOfBeiyong);
                        Marker marker1=chuli1.getMarker();
                        chuli1.setLatLng(latLng);
                        marker1.setPosition(latLng);
                        beiyongList.set(indexOfBeiyong,chuli1);
                    }
                }
            }
        }
    }
    //拖拽备用的航点,手松开后，该航点会成为正式的航点，同时生成新的航点
    //第一个参数为存储所有航点的list，第二个航点为存储备用航点的list,第三个参数为一个备用航点的Marker，第四个参数为修改备用航点Marker的图标
    private void tuozhuaiBeiyongHangdian(List<Airpoint> list,List<Airpoint> beiyongHangdian,
                                         Marker marker,BitmapDescriptor bdb,List<String> listOfAirpointOperation,RecycleAdapter adapter){
        //修改该marker对应的备用航点的信息
        int index=10000;
        Iterator<Airpoint> iterator=beiyongHangdian.iterator();
        while (iterator.hasNext()){
            Airpoint hangDianMessage1=iterator.next();
            if(hangDianMessage1.getMarker()==marker){
                index=beiyongHangdian.indexOf(hangDianMessage1);
                Marker marker1=marker;
                marker1.setIcon(bdb);
                hangDianMessage1.setMarker(marker1);
                hangDianMessage1.setLatLng(marker.getPosition());
                hh=hangDianMessage1;
                beiyongHangdian.set(index,hangDianMessage1);
            }
        }
        //先将备用航点移除，然后从加入到List中
        list.add(index+1,hh);
        //修改航点的ID
        for(int i=0;i<list.size();i++){
            Airpoint hangDianMessage1=list.get(i);
            hangDianMessage1.setId(i+1);
            list.set(i,hangDianMessage1);
        }
        //清除备用航点
        for(Airpoint hangDianMessage:beiyongHangdian){
            hangDianMessage.getMarker().remove();
        }
        beiyongHangdian.clear();
        //提示：一个经纬度上只有一个Mark，删除备用航点后，其对应的位置上的Mark也就没有了，必须加上下面这段代码
        OverlayOptions ooA = new MarkerOptions().position(hh.getLatLng()).icon(bdb)
                .zIndex(9).draggable(true);
        mMarkerA = (Marker) (map.addOverlay(ooA));
        hh.setMarker(mMarkerA);
        list.set(index+1,hh);
        //生成备用航点
        for(int k=0;k<list.size()-1;k++){
            generatePreAirpoint(list.get(k),list.get(k+1),beiyongHangdian);
        }
        //更新List信息
        listOfAirpointOperation.clear();
        for(Airpoint airpoint:list){
            listOfAirpointOperation.add(""+airpoint.getId());
        }
        adapter.notifyDataSetChanged();
    }
    //拖拽过程中的动作
    private void tuozhuaiBeiyongHangdianProcess(List<Airpoint> list,List<Airpoint> beiyongHangdian,Marker marker){
        //必须这样定义，如果将list的值赋给ll，那么list会随着ll的变化而变化
        List<Airpoint> ll=new ArrayList<>();
        int index=0;
        Airpoint hangDianMessage=new Airpoint();
        Iterator<Airpoint> iterator=beiyongHangdian.iterator();
        while (iterator.hasNext()){
            Airpoint hangdianMessage1=iterator.next();
            if(hangdianMessage1.getMarker()==marker){
                index=beiyongHangdian.indexOf(hangdianMessage1);
                //必须加上，如果不加上的话，后面生成航线的时候，由于该点的经纬度没有随着Marker位置的变化而变化，造成航线没有改变
                hangdianMessage1.setLatLng(marker.getPosition());
                beiyongHangdian.set(index,hangdianMessage1);
                hangDianMessage=hangdianMessage1;
            }
        }
        for(Airpoint hangDianMessage1:list){
            ll.add(hangDianMessage1);
        }
        ll.add(index+1,hangDianMessage);
        generateHangxian(ll);
    }
    //调整航点经纬度的后续动作
    private void adjustLocation(Airpoint hh,double valuetoLatitudeOfAirpoint,double valuetoLongitudeOfAirpoint){
        LatLng latLng=new LatLng(valuetoLatitudeOfAirpoint,valuetoLongitudeOfAirpoint);
        hh.setLatLng(latLng);
        hh.getMarker().setPosition(latLng);
        //重新绘制航线
        //1、修改对应航点
        listOfAirpoint.set(idOfSelectedAirpoint-1,hh);
        //2、清除以前准航点
        for(Airpoint airpoint:listOfPreAirpoint){airpoint.getMarker().remove();}
        listOfPreAirpoint.clear();
        //3、生成航线和准航点
        generateHangxian(listOfAirpoint);
        for(int i=0;i<listOfAirpoint.size()-1;i++){
            generatePreAirpoint(listOfAirpoint.get(i),listOfAirpoint.get(i+1),listOfPreAirpoint);
        }
        //航线长度更新
        distance=0;
        for(int i=0;i<listOfAirpoint.size()-1;i++){
            distance+=(int) DistanceUtil.getDistance(listOfAirpoint.get(i).getLatLng(), listOfAirpoint.get(i+1).getLatLng());
        }
        timeOfFlying.setText(""+distance+"m(未除速度)");//飞行时间(暂且显示路径长度)
        lengtOfAirLine.setText(""+distance+"m");//路线长度
    }
    //生成航点任务的操作
    private void generateTaskOfAirpoint1(int sequence,int parameter,String message,Airpoint airpoint,List<TaskofAirpoint> listOfAirpointTaskAction,
                                         MessageAdapter adapter){
        //如果航点任务列表中的item被选中，那么只是对其参数进行修改
        if(isClick){
            TaskofAirpoint taskofAirpoint1=new TaskofAirpoint();
            Iterator<TaskofAirpoint> iterator=listOfAirpointTaskAction.iterator();
            while (iterator.hasNext()){
                TaskofAirpoint taskofAirpoint=iterator.next();
                //获取该item对应的任务类，更改其参数
                if(taskofAirpoint.getID()==positionOfSelectedTask+1){
                    taskofAirpoint1=taskofAirpoint;
                    fuckposition=listOfAirpointTaskAction.indexOf(taskofAirpoint);
                    taskofAirpoint1.setParameter(parameter);
                }
            }
            isClick=false;
            //更新item的值
            listOfAirpointTaskAction.set(fuckposition,taskofAirpoint1);
            adapter.notifyDataSetChanged();
            airpoint.getListOfairpointAction1().set(fuckposition,listOfAirpointTaskAction.get(fuckposition));
        }else{
            //如果没有被选中，对其进项添加操作，首先构造任务类
            TaskofAirpoint taskofAirpoint=new TaskofAirpoint();
            taskofAirpoint.setSequence(sequence);
            taskofAirpoint.setID(listOfAirpointTaskAction.size()+1);
            taskofAirpoint.setParameter(parameter);
            taskofAirpoint.setMessage(message);
            //将其添加到列表中
            listOfAirpointTaskAction.add(taskofAirpoint);
            adapter.notifyDataSetChanged();
            airpoint.getListOfairpointAction1().add(taskofAirpoint);
        }

    }
    //改变两个航点的顺序
    private void changeSequenceOfAirpoint(int firstPosition,int lastPosition,List<Airpoint> listOfAirpoint,List<Airpoint> listOfPreAirpoint){
        Airpoint af=listOfAirpoint.get(firstPosition);
        Airpoint al=listOfAirpoint.get(lastPosition);
        //交换两个航点的顺序
        listOfAirpoint.set(firstPosition,al);
        listOfAirpoint.set(lastPosition,af);
        //更新航点的图标和ID
        for(int i=0;i<listOfAirpoint.size();i++){
            if(i==0){
                Airpoint airpoint=listOfAirpoint.get(i);
                airpoint.getMarker().setIcon(bdA);
                airpoint.setId(i+1);
            }else {
                Airpoint airpoint=listOfAirpoint.get(i);
                airpoint.getMarker().setIcon(bdb);
                airpoint.setId(i+1);
            }
        }
        //2、清除以前准航点
        for(Airpoint airpoint:listOfPreAirpoint){airpoint.getMarker().remove();}
        listOfPreAirpoint.clear();
        //3、生成航线和准航点
        generateHangxian(listOfAirpoint);
        for(int i=0;i<listOfAirpoint.size()-1;i++){
            generatePreAirpoint(listOfAirpoint.get(i),listOfAirpoint.get(i+1),listOfPreAirpoint);
        }
    }
    //对信息进行一系列操作
    private void messageOperation(){
        //执行退出操作
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutForTasktype.setVisibility(View.VISIBLE);
                layoutForMessageOperation.setVisibility(View.GONE);
                switch (btnFlag){
                    case 0:
                        //航点任务列表以及一系列与航点相关的布局消失
                        layoutOfAdjustAirpointLocation.setVisibility(View.GONE);
                        layoutOfAirpointTaskActionList.setVisibility(View.GONE);
                        layoutOfAirpointSetting.setVisibility(View.GONE);
                        layoutOfAirpointZone.setVisibility(View.GONE);
                        break;
                    case 1:
                        //区域任务布局以及对应的地图上的Marker消失
                        layoutForZonetask.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        });
        //执行保存信息操作
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //上传信息
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //任务开始执行
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //暂停或者继续任务
        btnPauseContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //一键返航
        btnReturnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    //定义个线程，不停地显示状态信息
    class MyThread implements Runnable{
        public void run(){
            while (true){
                try{
                    Thread.sleep(100);
                    Message message=new Message();
                    message.what=CMD_FLYINGSTATUS;
                    handler.sendMessage(message);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    //显示Toast信息
    private void showMessage(String s){
        Toast.makeText(MainInterfaceAnother7.this,s,Toast.LENGTH_SHORT).show();
    }
    //删除兴趣点
    private void deleteInterest(int position,List<String> list,RecycleAdapter adapter,List<InterestPoint> list1){
        if (position!=-1){
            for(String s:list){
                int position1=list.indexOf(s);
                if(position1>position){
                    int i=Integer.parseInt(s)-1;
                    list.set(position1,""+i);
                }
            }
            list.remove(position);
            adapter.notifyDataSetChanged();
            for(InterestPoint interestPoint:list1){
                int position1=list1.indexOf(interestPoint);
                if(position1>position){
                    int id=interestPoint.getId()-1;
                    interestPoint.setId(id);
                    list1.set(position1,interestPoint);
                }
            }
            list1.get(position).getMarker().remove();
            list1.remove(position);
        }

    }
    //区域任务的操作，scan增加的是扫描任务,围栏增加的是围栏任务，
    private void zoneOperation(){
        btnScan=(Button)findViewById(R.id.btn_scan);
        btnEnclosure=(Button)findViewById(R.id.btn_enclosure);
        btnAdd_Zone=(Button)findViewById(R.id.btn_add);
        btnDelete_Zone=(Button)findViewById(R.id.btn_delete);
        btnApply_Zone=(Button)findViewById(R.id.btn_apply);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               isScan=0;
            }
        });
        btnEnclosure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isScan=1;
            }
        });
        btnAdd_Zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(isScan){
                    case 0:
                        int size=listOfZonetask.size()+1;
                        LatLng latLng=new LatLng(22.652222,113.913056);
                        listOfZonetask.add(""+size);
                        adapterOfZonetask.notifyDataSetChanged();
                        List<Marker> list1=scanTaskOperation.generateMarker(latLng,map,bde);
                        List<Marker> list2=scanTaskOperation.generatePreMarker(map,list1,bdf);
                        mapOfMarker.put(""+size,list1);
                        mapOfPreMarker.put(""+size,list2);
                        overlay=scanTaskOperation.generateScanZone(overlay,list1,map);
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        });
    }
    //初始化所有的控件
    private void initAll(){
        //用于显示航点，区域，安全和设置列表
        btnOfAirline=(Button) findViewById(R.id.button_for_airpoint);
        btnOfZone=(Button) findViewById(R.id.button_for_quyu);
        btnOfSet=(Button) findViewById(R.id.button_for_set);
        layoutOfTaskZone=(LinearLayout) findViewById(R.id.id_of_task_zone);
        layoutOfGlobalSetOfAirpoint=(LinearLayout)findViewById(R.id.id_of_all_setting);
        //显示或者隐藏全局设置布局
        btnOfTaskParameterSet=(Button) findViewById(R.id.setting_of_task_parameter);
        btnOfAttitudeParameterSet=(Button) findViewById(R.id.setting_of_attitude_parameter);
        btnOfAirbornParameterSet=(Button)findViewById(R.id.setting_of_airborne_parameter);
        btnOfMountParameterSet=(Button)findViewById(R.id.setting_of_mount_parameter);
        btnOfPressKey=(Button)findViewById(R.id.setting_of_press_key);
        btnOfAlertSet=(Button)findViewById(R.id.setting_of_alert);

        layoutOfTaskParameterSet=(LinearLayout)findViewById(R.id.father_of_setting_of_task_parameter);
        layoutOfAttitudeParameterSet=(LinearLayout)findViewById(R.id.father_of_setting_of_attitude_parameter);
        layoutOfAirbornParameterSet=(LinearLayout) findViewById(R.id.father__of_setting_of_airborne_parameter);
        layoutOfPressKey=(LinearLayout) findViewById(R.id.father__of_setting_of_press_key);
        layoutOfAlertSet=(LinearLayout) findViewById(R.id.father__of_setting_of_alert);
        //姿态参数设置（伺服校准时使用）
        seekbarOfZoom=(SeekBar) findViewById(R.id.seekbar_zooming);
        seekbarOfRoll=(SeekBar) findViewById(R.id.seekbar_roll);
        seekbarOfPitch=(SeekBar)findViewById(R.id.seekbar_pitch);
        seekbarOfThrottle=(SeekBar) findViewById(R.id.seekbar_throttle);
        seekbarOfCameraYaw=(SeekBar)findViewById(R.id.seekbar_yaw_camera);
        seekbarOfCameraPitch=(SeekBar)findViewById(R.id.seekbar_pitch_camera);
        edittextOfZoom=(EditText)findViewById(R.id.edittext_zooming);
        edittextOfRoll=(EditText)findViewById(R.id.edittext_roll);
        edittextOfPitch=(EditText)findViewById(R.id.edittext_pitch);
        edittextOfThrottle=(EditText)findViewById(R.id.edittext_throttle);
        edittextOfCameraYaw=(EditText) findViewById(R.id.edittext_yaw_camera);
        edittextOfCameraPitch=(EditText) findViewById(R.id.edittext_pitch_camera);
        //任务信息初始化
        featureCode=(TextView) findViewById(R.id.feature_code);
        timeOfFlying=(TextView)findViewById(R.id.time_of_flying);
        numberOfAirpoint=(TextView)findViewById(R.id.number_of_airpoint);
        lengtOfAirLine=(TextView)findViewById(R.id.length_of_airline);
        //地理姿态设置初始化
        seekbarOfAltidueheight=(SeekBar) findViewById(R.id.seekbar_of_altitude_height);
        seekbarOfGroundHeight=(SeekBar)findViewById(R.id.seekbar_of_ground_height);
        seekbarOfUpSpeed=(SeekBar)findViewById(R.id.seekbar_of_rise_speed);
        seekbarOfDownSpeed=(SeekBar)findViewById(R.id.seekbar_of_fall_speed);
        seekbarOfFlyingSpeed=(SeekBar)findViewById(R.id.seekbar_of_flying_speed);

        textViewOfAltidueheight=(TextView)findViewById(R.id.value_of_altitude_height);
        textViewOfGroundHeight=(TextView)findViewById(R.id.value_of_ground_height);
        textViewOfUpSpeed=(TextView)findViewById(R.id.value_of_rise_speed);
        textViewOfDownSpeed=(TextView)findViewById(R.id.value_of_fall_speed);
        textViewOfFlyingSpeed=(TextView)findViewById(R.id.value_of_flying_speed);

        spinnerOfYawMode=(Spinner)findViewById(R.id.spinner_of_yaw_angle_mode);

        latitudeOfAirpoint=(TextView)findViewById(R.id.value_of_airpoint_latitude);
        longitudeOfAirpoint=(TextView)findViewById(R.id.value_of_airpoint_longitude);
        //任务列表操作控件初始化
        nameOfTask=(TextView) findViewById(R.id.textview_of_task_zone);
        listviewOfTask=(RecyclerView) findViewById(R.id.listview_of_task_zone);
        adapterOfTask=new RecycleAdapter(listOfTask);
        listviewOfTask.setAdapter(adapterOfTask);
        listviewOfTask.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        listviewOfTask.setHasFixedSize(true);
        //对任务列表进行操作和初始化
        adapterOfTask.setOnItemClickListener(new RecycleAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                switch (btnFlag){
                    case 0:
                        if(positionOfTasklist!=position){
                            //如果positionOfTasklist不为-1，那么listOfAirpoint和listOfPreAirpoint存储着上一个任务的信息
                            //将它们清空，装载该position对应的item对应的航点
                            for(Airpoint airpoint:listOfAirpoint){airpoint.getMarker().setVisible(false);}
                            for(Airpoint airpoint:listOfPreAirpoint){airpoint.getMarker().remove();}
                            listOfPreAirpoint.clear();
                            listOfAirpointOperation.clear();
                            String s1=listOfTask.get(position);
                            listOfAirpoint=hashmap.get(s1);
                            for(Airpoint airpoint:listOfAirpoint){airpoint.getMarker().setVisible(true);}
                            generateHangxian(listOfAirpoint);
                            for(int i=0;i<listOfAirpoint.size()-1;i++){
                                generatePreAirpoint(listOfAirpoint.get(i),listOfAirpoint.get(i+1),listOfPreAirpoint);
                            }
                            for(Airpoint airpoint:listOfAirpoint){listOfAirpointOperation.add(""+airpoint.getId());}
                            adapterOfAirpoint.notifyDataSetChanged();
                        }
                        positionOfTasklist=position;
                        showMessage("还有"+listOfAirpoint.size()+"个航点");
                        break;
                    case 1:
                        positionOfTasklist=position;
                        break;
                    default:
                        break;
                }

            }
        });
        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;//拖拽
                //int swipeFlags=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//侧滑
                return makeMovementFlags(dragFlags,0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(listOfTask,viewHolder.getAdapterPosition(),target.getAdapterPosition());
                int firstPosition=viewHolder.getAdapterPosition();
                int secondPosition=target.getAdapterPosition();

                adapterOfTask.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                String sf=listOfTask.get(firstPosition);
                String ss=listOfTask.get(secondPosition);
                List<Airpoint> lf=hashmap.get(sf);
                List<Airpoint> ls=hashmap.get(ss);
                listOfTask.set(firstPosition,ss);
                listOfTask.set(secondPosition,sf);
                adapterOfTask.notifyDataSetChanged();
                switch (btnFlag){
                    case 0:
                        hashmap.remove(sf);
                        hashmap.remove(ss);
                        hashmap.put(sf,ls);
                        hashmap.put(ss,lf);
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
            }
            public boolean isLongPressDragEnabled(){return true;}
        });
        helper.attachToRecyclerView(listviewOfTask);
        btnOfAddTask=(Button)findViewById(R.id.add_for_task_zone);
        btnOfDeleteTask=(Button)findViewById(R.id.delete_for_task_zone);
        btnOfEditTask=(Button)findViewById(R.id.edit_for_task_zone);
        //航点列表操作控件初始化
        btnOfDeleteAirpoint=(Button) findViewById(R.id.delete_for_airpoint_zone);
        btnOfDownAirpoint=(Button) findViewById(R.id.down_for_airpoint_zone);
        btnOfAirpoint=(Button) findViewById(R.id.btn_airpoint);
        btnOfInterest=(Button) findViewById(R.id.btn_interest);
        btnOfSelect=(Button) findViewById(R.id.btn_select_point);

        recyclerviewOfInterest=(RecyclerView) findViewById(R.id.recyclerview_of_interest);
        adapterOfInterest=new RecycleAdapter(listOfInterestOperation);
        recyclerviewOfInterest.setAdapter(adapterOfInterest);
        recyclerviewOfInterest.setLayoutManager(new LinearLayoutManager(this));
        adapterOfInterest.setOnItemClickListener(new RecycleAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                position2=position;
                deleteFlag=1;
            }
        });

        listViewOfAirpoint=(RecyclerView) findViewById(R.id.listview_of_airpoint_zone);
        adapterOfAirpoint=new RecycleAdapter(listOfAirpointOperation);
        listViewOfAirpoint.setAdapter(adapterOfAirpoint);
        listViewOfAirpoint.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        listViewOfAirpoint.setHasFixedSize(true);
        adapterOfAirpoint.setOnItemClickListener(new RecycleAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                position2=position;
                deleteFlag=0;
            }
        });
        ItemTouchHelper helper2=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;//拖拽
                return makeMovementFlags(dragFlags,0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(listOfAirpointOperation,viewHolder.getAdapterPosition(),target.getAdapterPosition());
                //获取交换位置的两个值
                int firstPosition=viewHolder.getAdapterPosition();
                int secondPosition=target.getAdapterPosition();
                adapterOfAirpoint.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                //交换顺序
                String sf=listOfAirpointOperation.get(firstPosition);
                String ss=listOfAirpointOperation.get(secondPosition);
                listOfAirpointOperation.set(firstPosition,ss);
                listOfAirpointOperation.set(secondPosition,sf);
                adapterOfAirpoint.notifyDataSetChanged();
                //更改对应航点的顺序
                changeSequenceOfAirpoint(firstPosition,secondPosition,listOfAirpoint,listOfPreAirpoint);
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
            }
            public boolean isLongPressDragEnabled(){return true;}
        });
        helper2.attachToRecyclerView(listViewOfAirpoint);

        //弹出任务列表窗口、航点信息设置窗口、位置调整窗口
        layoutOfAdjustAirpointLocation=(LinearLayout) findViewById(R.id.id_of_adjust_airpoint_location);
        layoutOfAirpointTaskActionList=(LinearLayout) findViewById(R.id.id_of_airpoint_task_action_list);
        layoutOfAirpointSetting=(LinearLayout) findViewById(R.id.id_of_airpoint_setting);
        layoutOfAirpointZone=(LinearLayout) findViewById(R.id.id_of_airpoint_zone);
        //航点机载设置参数初始化
        seekbarOfCamerapitch=(SeekBar) findViewById(R.id.seekbar_camera_pitch_angle_value);
        seekbarOfCameraroll=(SeekBar)findViewById(R.id.seekbar_camera_roll_angle_value);
        valueOfCamerapitch=(TextView)findViewById(R.id.value_of_camera_pitch_angle_value);
        valueOfCameraroll=(TextView)findViewById(R.id.value_of_camera_roll_angle_value);
        spinnerOftypeOfCamera=(Spinner)findViewById(R.id.spinner_of_camera_type);
        spinnerOfnumberOfCamera=(Spinner)findViewById(R.id.spinner_of_camera_serial_number);
        spinnerOfyawmodeOfCamera=(Spinner)findViewById(R.id.spinner_of_camera_yaw_angle_mode);
        //相机动作添加列表参数
        seekbarOfDelay=(SeekBar) findViewById(R.id.action_seekbar_of_delay);
        valueOfDelay=(TextView) findViewById(R.id.action_value_of_delay);
        seekbarOfActionyaw=(SeekBar) findViewById(R.id.action_seekbar_of_yaw);
        valueOfActionyaw=(TextView)findViewById(R.id.action_value_of_yaw);
        spinnerOfActionInterest=(Spinner) findViewById(R.id.action_spinner_of_intrest);
        seekabrOfActionFlyingheight=(SeekBar) findViewById(R.id.action_seekbar_of_flyingHeight);
        valueOfActionFlyingheight=(TextView)findViewById(R.id.action_value_of_flyingHeight);
        seekbarOfActionUpspeed=(SeekBar)findViewById(R.id.action_seekbar_of_upspeed);
        valueOfActionUpspeed=(TextView)findViewById(R.id.action_value_of_upspeed);
        seekbarOfActionDownspeed=(SeekBar)findViewById(R.id.action_seekbar_of_downspeed);
        valueOfActionDownspeed=(TextView)findViewById(R.id.action_value_of_downspeed);
        seekbarOfActionFlyingspeed=(SeekBar)findViewById(R.id.action_seekbar_of_flyingspeed);
        valueOfActionFlyingspeed=(TextView)findViewById(R.id.action_value_of_flyingspeed);
        spinnerOfActionTakeptoto=(Spinner) findViewById(R.id.action_takephoto);
        spinnerOfActionRecordVidio=(Spinner)findViewById(R.id.action_recordvideo);
        seekbarOfActionTakephotoDelay=(SeekBar)findViewById(R.id.action_seekbar_of_delay_takephoto);
        valueOfActionTakephotoDelay=(TextView)findViewById(R.id.action_value_of_delay_takephoto);
        seekbarOfActionCameraYaw=(SeekBar)findViewById(R.id.action_seekbar_of_camera_yaw);
        valueOfCameraYaw=(TextView)findViewById(R.id.action_value_of_camera_yaw);
        seekbarOfActionCameraPitch=(SeekBar)findViewById(R.id.action_seekbar_of_camera_pitch);
        valueOfCameraPitch=(TextView)findViewById(R.id.action_value_of_camera_pitch);
        spinnerOfFormatcamera=(Spinner) findViewById(R.id.action_camera_format);
        spinnerOfCameraNumber=(Spinner) findViewById(R.id.action_camera_number);
        spinnerOfCameraInterest=(Spinner)findViewById(R.id.action_camera_interest);
        spinnerOfSaving=(Spinner)findViewById(R.id.action_putdown_location);
        spinnerOfHook=(Spinner)findViewById(R.id.action_hook);
        spinnerOfDiaocang=(Spinner)findViewById(R.id.action_diaocang);
        spinnerOfHanhuaqi=(Spinner)findViewById(R.id.action_hanhuaqi);
        spinnerOfSearchLight=(Spinner)findViewById(R.id.action_searchlight);
        spinnerOfJiguang=(Spinner)findViewById(R.id.action_jiguangzhimang);
        spinnerOfWirelessRelay=(Spinner)findViewById(R.id.action_wirelessrelay);

        //航点动作添加列表参数初始化
        listviewOfAirpointTaskAction=(RecyclerView) findViewById(R.id.listview_of_airpoint_task);
        btnOfDownTaskAction=(Button)findViewById(R.id.down_for_task_action);
        btnOfDeleteTaskAction=(Button)findViewById(R.id.delete_for_task_action);
        mMessageAdapter=new MessageAdapter(listOfAirpointTaskAction1);
        listviewOfAirpointTaskAction.setAdapter(mMessageAdapter);
        listviewOfAirpointTaskAction.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        listviewOfAirpointTaskAction.setHasFixedSize(true);
        mMessageAdapter.setOnItemClickListener(new MessageAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                positionOfSelectedTask=position;
                isClick=true;
            }
        });
        ItemTouchHelper helper1=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;//拖拽
                return makeMovementFlags(dragFlags,0);
            }
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(listOfAirpointTaskAction1,viewHolder.getAdapterPosition(),target.getAdapterPosition());
                int first=viewHolder.getAdapterPosition();
                int second=target.getAdapterPosition();
                int firstId=listOfAirpointTaskAction1.get(first).getID();
                int secondId=listOfAirpointTaskAction1.get(second).getID();
                listOfAirpointTaskAction1.get(first).setID(secondId);
                listOfAirpointTaskAction1.get(second).setID(firstId);
                mMessageAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                for(int i=0;i<listOfAirpointTaskAction1.size();i++){
                    hh.getListOfairpointAction1().set(i,listOfAirpointTaskAction1.get(i));
                }
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
            }
            public boolean isLongPressDragEnabled(){return true;}
        });
        helper1.attachToRecyclerView(listviewOfAirpointTaskAction);
        //调整航点经纬度的控件初始化
        btnUnit=(Button)findViewById(R.id.adjust_unit);//单位
        btnAddLati=(Button)findViewById(R.id.latidude_add);//纬度加
        btnSubLati=(Button)findViewById(R.id.latidude_subtract);//纬度减
        btnAddLongi=(Button)findViewById(R.id.longitude_add);//经度加
        btnSubLongi=(Button)findViewById(R.id.longitude_subtract);//经度减

        layoutForTasktype=(LinearLayout) findViewById(R.id.id_of_task_type);
        layoutForMessageOperation=(LinearLayout) findViewById(R.id.id_of_message_operation11);
        layoutForZonetask=(LinearLayout) findViewById(R.id.id_of_zone_task);

        btnExit=(Button) findViewById(R.id.btn_exit);
        btnSave=(Button) findViewById(R.id.btn_saving);
        btnUpload=(Button) findViewById(R.id.btn_upload);
        btnExecute=(Button) findViewById(R.id.btn_execute);
        btnPauseContinue=(Button) findViewById(R.id.btn_pause_continue);
        btnReturnBack=(Button) findViewById(R.id.btn_returnback);

        rvOfZonetask=(RecyclerView) findViewById(R.id.recyclerview_zone_task);
        adapterOfZonetask=new RecycleAdapter(listOfZonetask);
        rvOfZonetask.setAdapter(adapterOfZonetask);
        rvOfZonetask.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvOfZonetask.setHasFixedSize(true);
        adapterOfZonetask.setOnItemClickListener(new RecycleAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                positionForZonetask=position;
            }
        });
        ItemTouchHelper helper3=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;//拖拽
                return makeMovementFlags(0,0);//不可上下左右移动
            }
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(listOfZonetask,viewHolder.getAdapterPosition(),target.getAdapterPosition());
                adapterOfZonetask.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
            }
            public boolean isLongPressDragEnabled(){return true;}
        });
        helper3.attachToRecyclerView(rvOfZonetask);

    }
}
