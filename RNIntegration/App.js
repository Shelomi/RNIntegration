import React from 'react'
import {
    AppRegistry,
    Text,
    View,
    Button,
    Platform,
    StyleSheet,
} from 'react-native'
import {StackNavigator} from 'react-navigation'

import ChatScreen from "./ChatScreen";
import MainScreenNavigator from './MainScreenNavigator'
import IpuBasic from './IpuBasic'

class HomeScreen extends React.Component{
    static navigationOptions = {
        title : 'Welcome'
    };

    constructor(props){
        super(props);
        this.state = {result:""};
    }

    render(){
        const { navigate } = this.props.navigation;

        return (
            <View>
                <Text>Hello, Chat App!</Text>
                <Button title="Chat With Lucy"
                        onPress={()=>navigate('Chat',{user:'Lucy Join'})}
                        style={styles.buttonLittle}
                />
                <View style={{width:10,height:100}}/>
                <Text>测试从ReactNative打开原生Activity</Text>
                <Button
                    title="从RN打开原生Activity"
                    onPress={()=>{
                        IpuBasic.startActivity('com.example.simon.rntestandroid.NativeActivity',{name:"123"});
                    }}

                    style={{width:100}}
                />

                <Text>测试从ReactNative打开原生Activity，并获取返回值</Text>
                <Button
                    title="从RN打开原生并获取返回值"
                    onPress={
                        ()=>{
                            IpuBasic.startActivityForResult('com.example.simon.rntestandroid.NativeActivity',{name:"123"},(val)=>{
                                this.setState({result:val})
                            });

                        }
                    }
                />
                <Text>返回值：{this.state.result}</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    buttonLittle:{
        color: 'blue',
        fontWeight: 'bold',
        fontSize: 10,
        width:200,
    }
});



const RNIntegration = StackNavigator({
    Home : {screen : HomeScreen},
    Chat : {
        screen : ChatScreen,
        path:'chat/:user',
        },
});

const prefix = Platform.OS === 'android' ? 'mychat://mychat/' : 'mychat://';
const MainApp = ()=><RNIntegration uriPrefix={prefix}/>

AppRegistry.registerComponent('RNIntegration',()=>MainApp)