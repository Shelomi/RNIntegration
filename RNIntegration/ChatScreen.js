import React from 'react'
import {
    Text,
    View,
    Button,
} from 'react-native'

import IpuBasic from './IpuBasic'

export default class ChatScreen extends React.Component{
    static navigationOptions = ({navigation}) =>{
      const {state,setParams} = navigation;
      const isInfo = state.params.mode === 'info';
      const {user} = state.params;

      return {
          title : isInfo ? `${user}'s Contact Info` : `Chat with ${user}`,
          headerRight : (
              <Button title={isInfo ? 'Done' : `${user}'s info`}
              onPress={()=>setParams({mode:isInfo ? 'none' : 'info'})}/>
          ),
      }
    };

    render(){
        const {params} = this.props.navigation.state;
        return (
            <View>
                <Text>Chat with {params.user}</Text>
                <Button
                    title="finish"
                    onPress={()=>{
                        IpuBasic.setResultAndFinish("这是从RN获取到的值");
                    }}
                />
            </View>
        );
    }
}